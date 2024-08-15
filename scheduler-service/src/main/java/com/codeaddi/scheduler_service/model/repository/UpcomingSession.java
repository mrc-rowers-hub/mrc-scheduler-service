package com.codeaddi.scheduler_service.model.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "upcoming_sessions")
@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upcoming_session_id")
    private Integer upcomingSessionId;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

}
