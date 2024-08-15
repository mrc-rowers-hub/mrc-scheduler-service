package com.codeaddi.scheduler_service.controller.db;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.codeaddi.scheduler_service.model.repository.sessions.SessionRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Session;
import com.codeaddi.scheduler_service.testUtils.TestData;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SessionsServiceTests {

  @Mock SessionRepository sessionRepository;

  @InjectMocks SessionsService sessionsService;

  @Test
  void findById_infoInDb_returnsSession() {
    Session expected =
        TestData.listOfSessions.stream()
            .filter(session -> session.getId().equals(1L))
            .findAny()
            .orElseThrow();

    when(sessionRepository.findById(1L)).thenReturn(Optional.of(expected));

    Session actual = sessionsService.findById(TestData.validId);

    assertThat(expected.equals(actual));
  }

  @Test()
  void findById_infoNotInDb_throwsException() {
    when(sessionRepository.findById(TestData.unknownId)).thenReturn(java.util.Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> sessionsService.findById(TestData.unknownId));
  }

  @Test()
  void replaceSession_sessionNotAlreadyInDb_throwsException() {
    when(sessionRepository.findById(TestData.unknownId)).thenReturn(java.util.Optional.empty());

    assertThrows(
        EntityNotFoundException.class,
        () -> sessionsService.replaceSession(TestData.unknownSession));
  }

  @Test()
  void replaceSession_sessionAlreadyInDb_replacesSession() {
    when(sessionRepository.findById(TestData.validId))
        .thenReturn(Optional.of(TestData.validSession));

    sessionsService.replaceSession(TestData.validSessionReplacement);

    ArgumentCaptor<Long> deleteArgumentCaptor = ArgumentCaptor.forClass(Long.class);
    verify(sessionRepository).deleteById(deleteArgumentCaptor.capture());
    Long deletedSessionId = deleteArgumentCaptor.getValue();
    assert deletedSessionId.equals(TestData.validId);

    verify(sessionRepository).save(TestData.validSessionReplacement);
  }

  @Test()
  void deleteSession_sessionInDb_deletesSession() {
    when(sessionRepository.existsById(TestData.unknownId)).thenReturn(true);

    sessionsService.deleteSession(TestData.unknownId);

    verify(sessionRepository, times(1)).deleteById(TestData.unknownId);
  }

  @Test
  void deleteSession_sessionDoesNotExist_throwsEntityNotFoundException() {

    when(sessionRepository.existsById(TestData.unknownId)).thenReturn(false);

    EntityNotFoundException exception =
        assertThrows(
            EntityNotFoundException.class,
            () -> {
              sessionsService.deleteSession(TestData.unknownId);
            });

    assertEquals("Session with id " + TestData.unknownId + " not found", exception.getMessage());

    verify(sessionRepository, never()).deleteById(TestData.unknownId);
  }
}
