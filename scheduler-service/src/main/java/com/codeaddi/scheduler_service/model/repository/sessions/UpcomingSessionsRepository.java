package com.codeaddi.scheduler_service.model.repository.sessions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpcomingSessionsRepository extends JpaRepository<UpcomingSession, Long> {


    @Modifying
    @Query(value = "CALL insert_upcoming_sessions_one_week_ahead(:start_date)", nativeQuery = true)
    void callInsertUpcomingSessions(@Param("start_date") java.sql.Date startDate);


    @Modifying
    @Query(value = "CALL insert_upcoming_sessions_by_id(:p_session_id)", nativeQuery = true)
    void initUpcomingFourWeeksForSession(@Param("p_session_id") Long sessionId);

    //need a sproc for just adding four weeks worth for a certain session

    @Query("SELECT u FROM UpcomingSession u WHERE u.date < CURRENT_DATE")
    List<UpcomingSession> findAllPastSessions();

    @Modifying
    @Query(value = "DELETE FROM UpcomingSession us WHERE us.sessionId = :sessionId", nativeQuery = true)
    void deleteBySessionId(@Param("sessionId") Long sessionId);


}

