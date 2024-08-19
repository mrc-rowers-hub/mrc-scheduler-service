package com.codeaddi.scheduler_service.model.http.outbound;

import com.codeaddi.scheduler_service.model.enums.RowerLevel;
import com.codeaddi.scheduler_service.model.enums.SessionType;
import com.codeaddi.scheduler_service.model.enums.Squad;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class UpcomingAvailabilityDTO {

  @JsonProperty("upcoming_session_id")
  private Long upcomingSessionId;

  private Date date;

  @JsonProperty("start_time")
  private LocalTime startTime;

  @JsonProperty("end_time")
  private LocalTime endTime;

  private Squad squad;

  private RowerLevel level;

  @JsonProperty("session_type")
  private SessionType sessionType;
}
