package com.codeaddi.scheduler_service.controller.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.codeaddi.scheduler_service.model.repository.sessions.PastSessionsRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.PastSession;
import com.codeaddi.scheduler_service.testUtils.TestData;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PastSessionsServiceTests {

  @Mock PastSessionsRepository pastSessionsRepository;

  @InjectMocks PastSessionsService pastSessionsService;

  @Test
  void getAllPastSessionsNotYetOccurred_dataInDb_returnsAllSessionsNotYetOccurred() {
    when(pastSessionsRepository.findAll())
        .thenReturn(List.of(TestData.pastSessionJustGone, TestData.pastSessionNotYetOccurred));

    List<PastSession> expected = List.of(TestData.pastSessionNotYetOccurred);
    List<PastSession> actual = pastSessionsService.getAllPastSessionsNotYetOccurred();

    assertEquals(expected.size(), actual.size());
    System.out.println(actual.getFirst().getSessionId());
    assertTrue(expected.getFirst().getSessionId().equals(actual.getFirst().getSessionId()));
  }
}
