package com.kaybee.franky.service;

import com.kaybee.franky.kafka.producer.ProducerService;
import com.kaybee.franky.model.LogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogIngestor implements ILogIngestor {

    @Autowired
    private ProducerService producerService;

    private static final String TOPIC_NAME = "topic-logs";

    @Override
    public void ingestLogs(LogData logData) {
        // publish log data to kafka broker
        producerService.sendMessage(TOPIC_NAME, logData);
    }

    @Override
    public void ingestLogs(LogData[] logDataList) {
        producerService.sendMessage(TOPIC_NAME, logDataList);
    }
}
