package com.nidecki.databasesynchronization;

import com.nidecki.databasesynchronization.scheduler.DatabaseSynchronization;
import com.nidecki.databasesynchronization.service.DatabaseMain;
import com.nidecki.databasesynchronization.service.DatabaseReplica;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DatabaseSynchronizationApplicationTests {

    @Autowired
    private DatabaseMain databaseMain;
    @Autowired
    private DatabaseReplica databaseReplica;
    @Autowired
    private DatabaseSynchronization databaseSynchronization;

    @Test
    void contextLoads() {
    }

    @Test
    void synchronizeTheDatabase() {
        //Given & When
        databaseMain.saveText("Test");
        databaseMain.saveText("Test 2");
        databaseSynchronization.synchronizeTheDatabase();

        //Then
        await().atLeast(Duration.ofSeconds(5));
        int result = databaseReplica.count();
        assertEquals(2, result);

        //CleanUp
        databaseMain.remove("Text");
        databaseMain.remove("Text 2");
        databaseReplica.remove("Text");
        databaseReplica.remove("Text 2");
    }
}
