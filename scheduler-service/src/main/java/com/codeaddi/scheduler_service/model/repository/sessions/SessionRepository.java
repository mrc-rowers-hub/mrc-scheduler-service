package com.codeaddi.scheduler_service.model.repository.sessions;

import com.codeaddi.scheduler_service.model.repository.sessions.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {}
