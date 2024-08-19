package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.http.outbound.StandardResponse;
import com.codeaddi.scheduler_service.model.http.outbound.enums.Status;
import com.codeaddi.scheduler_service.model.repository.sessions.SessionRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.UpcomingSessionsAvailabilityRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSessionAvailability;
import com.codeaddi.scheduler_service.testUtils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AvailabilityServiceTests {

    @Mock
    UpcomingSessionsAvailabilityRepository upcomingSessionsAvailabilityRepository;

    @InjectMocks
    AvailabilityService availabilityService;


    @Test
    void saveAvailability_noAvailabilityExists_savesAvailability(){
        when(upcomingSessionsAvailabilityRepository.findUpcomingSessionAvailabilitiesByRowerIdAndUpcomingSessionId(anyLong(), anyLong()))
                .thenReturn(null);

        StandardResponse response = availabilityService.saveAvailability(TestData.availabilityDTORowerAvailable);

        assertEquals(TestData.standardResponseAvailabilityAdded.getStatus(), response.getStatus());
        assertEquals(TestData.standardResponseAvailabilityAdded.getMessage(), response.getMessage());
        verify(upcomingSessionsAvailabilityRepository, times(1)).save(any(UpcomingSessionAvailability.class));
    }

    @Test
    void saveAvailability_availabilityAlreadyExists_updatesAvailability(){
        when(upcomingSessionsAvailabilityRepository.findUpcomingSessionAvailabilitiesByRowerIdAndUpcomingSessionId(anyLong(), anyLong()))
                .thenReturn(TestData.existingAvailability);

        StandardResponse response = availabilityService.saveAvailability(TestData.availabilityDTORowerAvailable);

        assertEquals(TestData.standardResponseAvailabilityNotUpdated.getStatus(), response.getStatus());
        assertEquals(TestData.standardResponseAvailabilityNotUpdated.getMessage(), response.getMessage());
        verify(upcomingSessionsAvailabilityRepository, never()).save(any(UpcomingSessionAvailability.class));
        verify(upcomingSessionsAvailabilityRepository, never()).delete(any(UpcomingSessionAvailability.class));

    }

    @Test
    void saveAvailability_availabilityExistsANDRowerNowUnavailable_savesAvailability(){
        when(upcomingSessionsAvailabilityRepository.findUpcomingSessionAvailabilitiesByRowerIdAndUpcomingSessionId(anyLong(), anyLong()))
                .thenReturn(TestData.existingAvailability);

        doNothing().when(upcomingSessionsAvailabilityRepository).delete(TestData.existingAvailability);

        StandardResponse response = availabilityService.saveAvailability(TestData.availabilityDTORowerUnavailable);

        assertEquals(TestData.standardResponseAvailabilityUpdatedToUnavailable.getStatus(), response.getStatus());
        assertEquals(TestData.standardResponseAvailabilityUpdatedToUnavailable.getMessage(), response.getMessage());
        verify(upcomingSessionsAvailabilityRepository, never()).save(any(UpcomingSessionAvailability.class));
        verify(upcomingSessionsAvailabilityRepository, times(1)).delete(TestData.existingAvailability);
    }





}
