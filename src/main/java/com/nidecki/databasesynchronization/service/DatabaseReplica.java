package com.nidecki.databasesynchronization.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class DatabaseReplica {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseReplica.class);

    private ArrayList<String> dataBase = new ArrayList<>();

    public String saveText(String text) {
        dataBase.add(text);
        return text;
    }

    public ArrayList<String> findAll() {
        return  dataBase;
    }

    public ArrayList<String> saveAll(ArrayList<String> allText) {
        LOGGER.info("Saving " + allText.size() + " to replica data base.");
        dataBase.addAll(allText);
        return dataBase;
    }

    public int count() {
        return dataBase.size();
    }
}
