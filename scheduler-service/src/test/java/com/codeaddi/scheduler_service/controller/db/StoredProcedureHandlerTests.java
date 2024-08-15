package com.codeaddi.scheduler_service.controller.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoredProcedureHandlerTests {

    @Autowired
    StoredProcedureHandler storedProcedureHandler;

    @Test
    void testing(){
        storedProcedureHandler.initAddFourWeeksOfUpcomingSessions();
    }

}
