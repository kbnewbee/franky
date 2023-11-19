package com.kaybee.franky.kafka.consumer;

import com.kaybee.franky.model.LogData;
import com.kaybee.franky.processor.LogProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ConsumerService {

    @Autowired
    private LogProcessor logProcessor;

    private static final String TOPIC_NAME = "topic-franky";
    private static final String GROUP_ID = "group-logs";
    private static final String CONCURRENCY = "3";

    private final ExecutorService executorService = Executors.newFixedThreadPool(30);

    @KafkaListener(
            topics = {TOPIC_NAME},
            groupId = GROUP_ID,
            concurrency = CONCURRENCY
    )
    public void consumeMessage(final LogData logData) {
        System.out.println("Consumed = " + logData);
        executorService.submit(() -> {
            logProcessor.process(logData);
        });
    }
}
