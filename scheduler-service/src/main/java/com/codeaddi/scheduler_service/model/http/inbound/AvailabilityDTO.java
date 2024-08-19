package com.codeaddi.scheduler_service.model.http.inbound;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AvailabilityDTO {
  //     Todo update the fields to have json strings
  private Long rowerId;
  private Long sessionId; // todo update to be upcoming session id
  private boolean availability;
}
