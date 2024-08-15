package com.codeaddi.scheduler_service.model.repository.sessions;

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
    private Long upcomingSessionId;

    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

}
