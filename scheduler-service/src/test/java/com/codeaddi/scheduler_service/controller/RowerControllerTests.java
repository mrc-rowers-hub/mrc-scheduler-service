package com.codeaddi.scheduler_service.controller;

import static org.mockito.Mockito.*;

import com.codeaddi.scheduler_service.controller.db.RowerService;
import com.codeaddi.scheduler_service.testUtils.TestData;
import com.codeaddi.scheduler_service.testUtils.TestUtils;
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
public class RowerControllerTests {

  private TestUtils testUtils = new TestUtils();

  @Mock RowerService rowerService;

  @InjectMocks RowerController rowerController;

  @BeforeEach
  public void setUpMockSessionController() {
    RestAssuredMockMvc.standaloneSetup(rowerController);
  }

  @Test
  void getAllRowers_dataInDb_returnsAllSessions() throws JSONException {
    when(rowerService.getAllRowers()).thenReturn(List.of(TestData.rower1));

    String expectedBody = testUtils.convertToJson(List.of(TestData.rower1));

    String actual =
        RestAssuredMockMvc.when()
            .get("/rowers/get_all")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .body()
            .asString();

    JSONAssert.assertEquals(expectedBody, actual, false);
  }
}
