package com.codeaddi.scheduler_service.model.repository.sessions.entities;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

@Entity
@Table(name = "past_sessions")
@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PastSession {
  // a class for sessions after the
  // cutoff time for availability,
  //  not necessarily in the past

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "upcoming_session_id")
  private Long upcomingSessionId;

  @Column(name = "session_id", nullable = false)
  private Long sessionId;

  @Column(name = "date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date date;
}
