package com.codeaddi.scheduler_service.controller;

import com.codeaddi.scheduler_service.controller.db.SessionsService;
import com.codeaddi.scheduler_service.testUtils.TestData;
import com.codeaddi.scheduler_service.testUtils.TestUtils;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
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

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

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
    void getAllSessions_returnsAllSessions() throws JSONException {
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

}
