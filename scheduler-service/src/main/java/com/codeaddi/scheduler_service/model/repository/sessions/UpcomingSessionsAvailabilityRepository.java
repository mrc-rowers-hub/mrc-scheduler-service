package com.codeaddi.scheduler_service.model.repository.sessions;

import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSessionAvailability;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UpcomingSessionsAvailabilityRepository
    extends JpaRepository<UpcomingSessionAvailability, Long> {

  @Modifying
  @Query(
      value = "DELETE FROM upcoming_sessions WHERE upcoming_session_id = :upcomingSessionId",
      nativeQuery = true)
  void deleteByUpcomingSessionId(@Param("upcomingSessionId") Long upcomingSessionId);

  List<UpcomingSessionAvailability> findUpcomingSessionAvailabilitiesByUpcomingSessionId(
      Long upcomingSessionId);

  UpcomingSessionAvailability findUpcomingSessionAvailabilitiesByRowerIdAndUpcomingSessionId(
      Long rowerId, Long upcomingSessionId);

  List<UpcomingSessionAvailability> findUpcomingSessionAvailabilitiesByRowerId(Long rowerId);
}
