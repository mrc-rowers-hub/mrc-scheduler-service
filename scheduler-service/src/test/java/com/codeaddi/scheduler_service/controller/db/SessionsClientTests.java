package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.testUtils.TestData;
import com.codeaddi.scheduler_service.model.repository.Session;
import com.codeaddi.scheduler_service.model.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SessionsClientTests {

    @Mock
    SessionRepository sessionRepository;

    @InjectMocks
    SessionsService sessionsService;

    @Test
    void findById_infoInDb_returnsSession(){
        Session expected = TestData.listOfSessions.stream().filter(session -> session.getId().equals(1L)).findAny().orElseThrow();

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(expected));

        Session actual = sessionsService.findById(TestData.validId);

        assertThat(expected.equals(actual));
    }

    @Test()
    void findById_infoNotInDb_throwsException(){
        when(sessionRepository.findById(TestData.unknownId)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sessionsService.findById(TestData.unknownId));
    }

    @Test()
    void replaceSession_sessionNotAlreadyInDb_throwsException(){
        when(sessionRepository.findById(TestData.unknownId)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sessionsService.replaceSession(TestData.unknownSession));
    }

    @Test()
    void replaceSession_sessionAlreadyInDb_replacesSession(){
        when(sessionRepository.findById(TestData.validId)).thenReturn(Optional.of(TestData.validSession));

        sessionsService.replaceSession(TestData.validSessionReplacement);

        ArgumentCaptor<Long> deleteArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(sessionRepository).deleteById(deleteArgumentCaptor.capture());
        Long deletedSessionId = deleteArgumentCaptor.getValue();
        assert deletedSessionId.equals(TestData.validId);

        verify(sessionRepository).save(TestData.validSessionReplacement);
    }

}
