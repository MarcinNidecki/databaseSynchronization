package com.nidecki.databasesynchronization.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DatabaseMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseReplica.class);

    private ArrayList<String> dataBase = new ArrayList<>();

    public String saveText(String text) {
        LOGGER.info("Saving one row to main data base.");
        dataBase.add(text);
        return text;
    }

    public ArrayList<String> findAll() {
        return new ArrayList<>(dataBase);
    }

    public int count() {
        return dataBase.size();
    }

    public void remove(String text) {
         dataBase.remove(text);
    }
}
