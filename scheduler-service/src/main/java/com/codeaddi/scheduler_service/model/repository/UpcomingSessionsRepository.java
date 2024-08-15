package com.codeaddi.scheduler_service.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UpcomingSessionsRepository extends JpaRepository<UpcomingSession, Long> {


    @Modifying
    @Query(value = "CALL insert_upcoming_sessions_one_week_ahead(:start_date)", nativeQuery = true)
    void callInsertUpcomingSessions(@Param("start_date") java.sql.Date startDate);


    @Modifying
    @Query(value = "CALL insert_upcoming_sessions()", nativeQuery = true)
    void initUpcomingFourWeeks();

    //need a sproc for just adding four weeks worth for a certain session

}

