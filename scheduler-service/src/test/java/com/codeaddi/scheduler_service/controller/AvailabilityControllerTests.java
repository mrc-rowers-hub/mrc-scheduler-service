package com.codeaddi.scheduler_service.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.codeaddi.scheduler_service.controller.db.AvailabilityService;
import com.codeaddi.scheduler_service.controller.db.PastAvailabilityService;
import com.codeaddi.scheduler_service.model.http.inbound.AvailabilityDTO;
import com.codeaddi.scheduler_service.testUtils.TestData;
import com.codeaddi.scheduler_service.testUtils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.List;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AvailabilityControllerTests {

  private TestUtils testUtils = new TestUtils();

  @Mock AvailabilityService availabilityService;
  @Mock
  PastAvailabilityService pastAvailabilityService;

  @InjectMocks AvailabilityController availabilityController;

  @BeforeEach
  public void setUpMockSessionController() {
    RestAssuredMockMvc.standaloneSetup(availabilityController);
  }

  @Test
  void
      saveAvailability_singleDtoSuppliedANDavailabilityProvidedForNotAlreadyAvailable_returnsAvailability()
          throws JSONException {
    doReturn(TestData.standardResponseAvailabilityAdded)
        .when(availabilityService)
        .saveAvailability(any(AvailabilityDTO.class));

    String requestBody = testUtils.convertToJson(List.of(TestData.availabilityDTORowerAvailable));

    String expectedResponse =
        testUtils.convertToJson(List.of(TestData.standardResponseAvailabilityAdded));

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
  void
      saveAvailability_multipleDtoSuppliedANDavailabilityProvidedForNotAlreadyAvailable_returnsAvailability()
          throws JSONException {
    doReturn(TestData.standardResponseAvailabilityAdded)
        .when(availabilityService)
        .saveAvailability(any(AvailabilityDTO.class));

    String requestBody =
        testUtils.convertToJson(
            List.of(
                TestData.availabilityDTORowerAvailable, TestData.availabilityDTORowerAvailable));

    String expectedResponse =
        testUtils.convertToJson(
            List.of(
                TestData.standardResponseAvailabilityAdded,
                TestData.standardResponseAvailabilityAdded));

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
  void getRowersAvailability_dataInDb_returnsAllSessions() throws JSONException {
    when(pastAvailabilityService.getAllPastAvailability()).thenReturn(List.of(TestData.pastSessionAvailability1));

    String expectedBody = testUtils.convertToJson(List.of(TestData.pastSessionAvailability1));

    String actual =
            RestAssuredMockMvc.when()
                    .get("/session_availability/get_rowers_availability")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .extract()
                    .body()
                    .asString();

    JSONAssert.assertEquals(expectedBody, actual, false);
  }
}
