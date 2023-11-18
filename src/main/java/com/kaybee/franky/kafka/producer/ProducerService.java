package com.kaybee.franky.kafka.producer;

import com.kaybee.franky.model.LogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, LogData> kafkaTemplate;

    private final ExecutorService executorService = Executors.newFixedThreadPool(30);

    public void sendMessage(String topic, LogData logData) {

        ListenableFuture<SendResult<String, LogData>> result =
                kafkaTemplate.send(topic, logData.getMetadata().getParentResourceId(), logData);

        result.addCallback(r -> {
            if (r != null && r.getProducerRecord() != null) {
                System.out.println("Partition = " + r.getRecordMetadata().partition());
                System.out.println("Key = " + r.getProducerRecord().key());
                System.out.println("log data = " + r.getProducerRecord().value());
            }
        }, Throwable::printStackTrace);
    }

    public void sendMessage(String topic, LogData[] logDataList) {

        for (LogData logData : logDataList) {
            ListenableFuture<SendResult<String, LogData>> result =
                    kafkaTemplate.send(topic, logData.getMetadata().getParentResourceId(), logData);

            result.addCallback(r -> {
                if (r != null && r.getProducerRecord() != null) {
                    System.out.println("Partition = " + r.getRecordMetadata().partition());
                    System.out.println("Key = " + r.getProducerRecord().key());
                    System.out.println("log data = " + r.getProducerRecord().value());
                }
            }, Throwable::printStackTrace);
        }
    }

}
