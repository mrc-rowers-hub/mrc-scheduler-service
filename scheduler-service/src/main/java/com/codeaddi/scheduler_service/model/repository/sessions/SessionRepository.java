package com.codeaddi.scheduler_service.model.repository.sessions;

import com.codeaddi.scheduler_service.model.enums.RowerLevel;
import com.codeaddi.scheduler_service.model.enums.SessionType;
import com.codeaddi.scheduler_service.model.enums.Squad;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Session;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
  @Query(
      "SELECT s FROM Session s WHERE s.day = :day AND s.startTime = :startTime AND s.endTime = :endTime AND s.squad = :squad AND s.level = :level AND s.sessionType = :sessionType")
  List<Session> findByCriteria(
      @Param("day") String day,
      @Param("startTime") LocalTime startTime,
      @Param("endTime") LocalTime endTime,
      @Param("squad") Squad squad,
      @Param("level") RowerLevel level,
      @Param("sessionType") SessionType sessionType);
}
