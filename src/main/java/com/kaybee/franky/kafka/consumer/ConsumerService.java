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
    private final ExecutorService executorService = Executors.newFixedThreadPool(30);

    @KafkaListener(
            topics = {"topic-logs"},
            groupId = "group-id-1",
            concurrency = "3"
    )
    public void consumeMessage(LogData logData) {
        System.out.println("Consumed = " + logData);
        executorService.submit(()->{
            logProcessor.process(logData);
        });
    }
}
