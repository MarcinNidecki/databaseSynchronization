package com.nidecki.databasesynchronization.controller;

import com.nidecki.databasesynchronization.service.DatabaseMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/text")
public class DataReceiver {

    @Autowired
    private DatabaseMain database;

    @PostMapping
    public  void save(@RequestParam String text) {
        database.saveText(text);
    }
}
