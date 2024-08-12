package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.enums.RowerLevel;
import com.codeaddi.scheduler_service.model.enums.SessionType;
import com.codeaddi.scheduler_service.model.enums.Squad;
import com.codeaddi.scheduler_service.model.repository.Session;
import com.codeaddi.scheduler_service.model.repository.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SessionsClientTests {

    @Mock
    SessionRepository sessionRepository;

    @InjectMocks
    SessionsClient sessionsClient;

    @Test
    void getAllSessions_infoInDb_returnsAllSessions(){
        when(sessionRepository.findAll()).thenReturn(setupSessions());

        List<Session> actual = sessionsClient.getAllSessions();

        assertThat(setupSessions().equals(actual));
    }

    private List<Session> setupSessions(){

        Session session1 = Session.builder().id(1L).day("MONDAY").startTime(LocalTime.NOON).endTime(LocalTime.MIDNIGHT).squad(Squad.DEVELOPMENT).level(RowerLevel.DEVELOPMENT).sessionType(SessionType.WATER).build();

        Session session2 = Session.builder().id(2L).day("MONDAY").startTime(LocalTime.NOON).endTime(LocalTime.MIDNIGHT).squad(Squad.WOMENS).level(RowerLevel.NOVICE).sessionType(SessionType.ERG).build();

        return List.of(session1, session2);

    }

}
