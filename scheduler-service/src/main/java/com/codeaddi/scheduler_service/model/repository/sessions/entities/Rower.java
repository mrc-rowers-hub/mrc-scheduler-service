package com.codeaddi.scheduler_service.model.repository.sessions.entities;

import com.codeaddi.scheduler_service.model.enums.RowerLevel;
import com.codeaddi.scheduler_service.model.enums.Squad;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rowers")
@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rower {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rower_id")
  private Long rowerId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "squad", columnDefinition = "ENUM('WOMENS', 'DEVELOPMENT', 'MENS')")
  @Enumerated(EnumType.STRING)
  private Squad squad;

  @Column(
          name = "level",
          columnDefinition = "ENUM('DEVELOPMENT', 'NOVICE', 'INTERMEDIATE', 'SENIOR')")
  @Enumerated(EnumType.STRING)
  private RowerLevel level;
}
