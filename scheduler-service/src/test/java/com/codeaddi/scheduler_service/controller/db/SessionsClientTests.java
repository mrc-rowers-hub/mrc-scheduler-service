package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.testUtils.TestData;
import com.codeaddi.scheduler_service.model.enums.RowerLevel;
import com.codeaddi.scheduler_service.model.enums.SessionType;
import com.codeaddi.scheduler_service.model.enums.Squad;
import com.codeaddi.scheduler_service.model.repository.Session;
import com.codeaddi.scheduler_service.model.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SessionsClientTests {


    @Mock
    SessionRepository sessionRepository;

    @InjectMocks
    SessionsService sessionsClient;

    @Test
    void getAllSessions_infoInDb_returnsAllSessions(){
        when(sessionRepository.findAll()).thenReturn(TestData.listOfSessions);

        List<Session> actual = sessionsClient.getAllSessions();

        assertThat(TestData.listOfSessions.equals(actual));
    }

    @Test
    void findById_infoInDb_returnsSession(){
        Session expected = TestData.listOfSessions.stream().filter(session -> session.getId().equals(1L)).findAny().orElseThrow();

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(expected));

        Session actual = sessionsClient.findById(1L);

        assertThat(expected.equals(actual));
    }

    @Test()
    void findById_infoNotInDb_throwsException(){
        when(sessionRepository.findById(3L)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sessionsClient.findById(3L));
    }

    @Test()
    void replaceSession_sessionNotAlreadyInDb_throwsException(){
        when(sessionRepository.findById(3L)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sessionsClient.replaceSession(TestData.unknownSession));
    }

    @Test()
    void replaceSession_sessionAlreadyInDb_replacesSession(){
        when(sessionRepository.findById(3L)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sessionsClient.replaceSession(TestData.unknownSession));
    }

}
