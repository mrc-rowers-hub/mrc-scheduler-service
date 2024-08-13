package com.codeaddi.scheduler_service.controller;

import com.codeaddi.scheduler_service.controller.db.SessionsService;
import com.codeaddi.scheduler_service.model.repository.Session;
import com.codeaddi.scheduler_service.testUtils.TestData;
import com.codeaddi.scheduler_service.testUtils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import jakarta.persistence.EntityNotFoundException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SessionControllerTests {

    private TestUtils testUtils = new TestUtils();

    @Mock
    SessionsService sessionsService;

    @InjectMocks
    SessionController sessionController;

    @BeforeEach
    public void setUpMockSessionController() {
        RestAssuredMockMvc.standaloneSetup(sessionController);
    }

    @Test
    void getAllSessions_dataInDb_returnsAllSessions() throws JSONException {
        when(sessionsService.getAllSessions()).thenReturn(TestData.listOfSessions);

        String expectedBody = testUtils.convertSessionsToJson(TestData.listOfSessions);

        String actual = RestAssuredMockMvc
                .when()
                .get("/standard_sessions/get_all_sessions")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().body().asString();

        JSONAssert.assertEquals(expectedBody, actual, false);
    }

    @Test
    void getAllSessions_noDataInDb_returnsAllSessions() throws JSONException {
        when(sessionsService.getAllSessions()).thenReturn(List.of());


        RestAssuredMockMvc
                .when()
                .get("/standard_sessions/get_all_sessions")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

    }

    @Test
    void updateSession_sessionExists_returnsSuccessResponse() throws JSONException {
        doNothing().when(sessionsService).replaceSession(any(Session.class));

        String requestBody = testUtils.convertSessionToJson(TestData.validSession);
        String expectedResponse = "{\"status\":\"SUCCESS\",\"message\":\"Session replaced\"}";

        String actualResponse = RestAssuredMockMvc
                .given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when()
                .put("/standard_sessions/update_session")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().body().asString();


        JSONAssert.assertEquals(expectedResponse, actualResponse, false);

    }

    @Test
    void updateSession_sessionDoesNotExist_returnsSuccessResponse() throws JSONException {
        doThrow(new EntityNotFoundException("Session not found")).when(sessionsService).replaceSession(any(Session.class));
        doNothing().when(sessionsService).addSession(any(Session.class));

        String requestBody = testUtils.convertSessionToJson(TestData.validSession);
        String expectedResponse = "{\"status\":\"SUCCESS_WITH_WARNING\",\"message\":\"Session not found, new session made\"}";

        String actualResponse = RestAssuredMockMvc
                .given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when()
                .put("/standard_sessions/update_session")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().body().asString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);

        verify(sessionsService, times(1)).addSession(any(Session.class));

    }



}
