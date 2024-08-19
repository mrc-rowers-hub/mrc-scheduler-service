package com.codeaddi.scheduler_service.controller;

import com.codeaddi.scheduler_service.controller.db.AvailabilityService;
import com.codeaddi.scheduler_service.controller.db.SessionsService;
import com.codeaddi.scheduler_service.model.http.inbound.AvailabilityDTO;
import com.codeaddi.scheduler_service.testUtils.TestData;
import com.codeaddi.scheduler_service.testUtils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AvailabilityControllerTests {

    private TestUtils testUtils = new TestUtils();


    @Mock
    AvailabilityService availabilityService;

    @InjectMocks
AvailabilityController availabilityController;

    @BeforeEach
    public void setUpMockSessionController() {
        RestAssuredMockMvc.standaloneSetup(availabilityController);
    }

    @Test
    void saveAvailability_singleDtoSuppliedANDavailabilityProvidedForNotAlreadyAvailable_returnsAvailability() throws JSONException {
        doReturn(TestData.standardResponseAvailabilityAdded)
                .when(availabilityService)
                .saveAvailability(any(AvailabilityDTO.class));

        String requestBody = testUtils.convertToJson(List.of(TestData.availabilityDTORowerAvailable));

        String expectedResponse = testUtils.convertToJson(List.of(TestData.standardResponseAvailabilityAdded));

        String actualResponse =
                given()
                        .body(requestBody)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/session_availability/save_availability")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .body()
                        .asString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @Test
    void saveAvailability_multipleDtoSuppliedANDavailabilityProvidedForNotAlreadyAvailable_returnsAvailability() throws JSONException {
        doReturn(TestData.standardResponseAvailabilityAdded)
                .when(availabilityService)
                .saveAvailability(any(AvailabilityDTO.class));

        String requestBody = testUtils.convertToJson(List.of(TestData.availabilityDTORowerAvailable, TestData.availabilityDTORowerAvailable));

        String expectedResponse = testUtils.convertToJson(List.of(TestData.standardResponseAvailabilityAdded, TestData.standardResponseAvailabilityAdded));

        String actualResponse =
                given()
                        .body(requestBody)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/session_availability/save_availability")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .body()
                        .asString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }
}
