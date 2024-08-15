package com.codeaddi.scheduler_service.model.repository.sessions;

import com.codeaddi.scheduler_service.model.enums.RowerLevel;
import com.codeaddi.scheduler_service.model.enums.SessionType;
import com.codeaddi.scheduler_service.model.enums.Squad;
import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.*;

@Entity
@Table(name = "sessions")
@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "session_id")
  private Long id;

  @Column(name = "day")
  private String day;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "end_time")
  private LocalTime endTime;

  @Column(name = "squad", columnDefinition = "ENUM('WOMENS', 'DEVELOPMENT', 'MENS')")
  @Enumerated(EnumType.STRING)
  private Squad squad;

  @Column(
      name = "level",
      columnDefinition = "ENUM('DEVELOPMENT', 'NOVICE', 'INTERMEDIATE', 'SENIOR')")
  @Enumerated(EnumType.STRING)
  private RowerLevel level;

  @Column(name = "session_type", columnDefinition = "ENUM('WATER', 'ERG', 'OTHER')")
  @Enumerated(EnumType.STRING)
  private SessionType sessionType;
}
