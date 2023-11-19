package com.kaybee.franky.controller;

import com.kaybee.franky.model.LogData;
import com.kaybee.franky.processor.LogProcessor;
import com.kaybee.franky.service.LogIngestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogIngestController {

    @Autowired
    private LogIngestor logIngestor;

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestLog(@RequestBody LogData logData) {
        if (logData != null) {
            logIngestor.ingestLogs(logData);
            return ResponseEntity.status(HttpStatus.OK).body("Log Data is ingested successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid log format provided");
    }

    @PostMapping("/ingest/batch")
    public ResponseEntity<String> ingestLogBatch(@RequestBody LogData[] logDataList) {
        if (logDataList.length > 0) {
            logIngestor.ingestLogs(logDataList);
            return ResponseEntity.status(HttpStatus.OK).body("Log Data is ingested successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid log format provided");
    }
}
