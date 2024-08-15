package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.sessions.PastSession;
import com.codeaddi.scheduler_service.model.repository.sessions.PastSessionsRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.UpcomingSession;
import com.codeaddi.scheduler_service.model.repository.sessions.UpcomingSessionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UpcomingSessionsService {

    @Autowired
    UpcomingSessionsRepository upcomingSessionsRepository;

    @Autowired
    PastSessionsRepository pastSessionsRepository;

    @Autowired
    StoredProcedureHandler storedProcedureHandler;

    public void addNewWeekOfUpcomingSessions(){
        moveOldSessions();

        storedProcedureHandler.updateUpcomingSessions(Date.from(Instant.now()));
    }

    private void moveOldSessions(){
        List<UpcomingSession> pastSessions = upcomingSessionsRepository.findAllPastSessions();

        for(UpcomingSession upcomingSession : pastSessions){
            PastSession pastSession = PastSession.builder().upcomingSessionId(upcomingSession.getUpcomingSessionId()).sessionId(upcomingSession.getSessionId()).date(upcomingSession.getDate()).build();
            pastSessionsRepository.save(pastSession);
            log.info("Upcoming session {} moved to past sessions", upcomingSession.getUpcomingSessionId());

            upcomingSessionsRepository.delete(upcomingSession);
            log.info("Removed upcoming session {} from upcoming sessions",upcomingSession.getUpcomingSessionId() );
        }
    }

    public void initAddFourWeeksOfUpcomingSessions(Long sessionId){
        storedProcedureHandler.initAddFourWeeksOfUpcomingSessions(sessionId);
    }

    public void removeUpcomingSessionsForRemovedSession(Long sessionId){
        upcomingSessionsRepository.deleteBySessionId(sessionId);
        log.info("Deleting upcoming sessions for removed ID {}", sessionId);
    }

}
