package com.codeaddi.scheduler_service.model.repository.sessions.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "upcoming_session_availability")
@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingSessionAvailability {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "upcoming_session_id", nullable = false)
  private Long upcomingSessionId;

  @Column(name = "rower_id", nullable = false)
  private Long rowerId;
}
