package com.codeaddi.scheduler_service.model.repository.sessions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PastSessionsRepository extends JpaRepository<PastSession, Long> {



}

