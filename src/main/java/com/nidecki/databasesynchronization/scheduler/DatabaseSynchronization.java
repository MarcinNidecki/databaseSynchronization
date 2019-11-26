package com.nidecki.databasesynchronization.scheduler;

import com.nidecki.databasesynchronization.service.DatabaseMain;
import com.nidecki.databasesynchronization.service.DatabaseReplica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSynchronization {

    @Autowired
    private DatabaseMain databaseMain;
    @Autowired
    private DatabaseReplica databaseReplica;

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseReplica.class);

    @Scheduled(fixedDelay=5000)
    public boolean synchronizeTheDatabase() {
        LOGGER.info("Synchronizing databases...");
        if (databaseMain.count() > databaseReplica.count()) {
            ArrayList<String> mainDatabase = databaseMain.findAll();
            List<String> replicaDatabase = databaseReplica.findAll();
            mainDatabase.removeAll(replicaDatabase);
            databaseReplica.saveAll(mainDatabase);
            }
        LOGGER.info("Synchronizing complete.");
        LOGGER.info("main    database: " + databaseMain.findAll().toString());
        LOGGER.info("replica database: " + databaseReplica.findAll().toString());
        if (databaseMain.findAll().equals(databaseReplica.findAll())) {
            return true;
        } else {
            return false;
        }
    }
}
