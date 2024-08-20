package com.codeaddi.scheduler_service.model.repository.sessions;

import com.codeaddi.scheduler_service.model.repository.sessions.entities.Rower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RowersRepository  extends JpaRepository<Rower, Long>  {
}
