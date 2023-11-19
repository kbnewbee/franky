package com.kaybee.franky.processor;

import com.kaybee.franky.model.LogData;
import com.kaybee.franky.repository.LogElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogProcessor {

    @Autowired
    private LogElasticRepository logElasticRepository;

    private void createLogIndex(LogData logData) {
        logElasticRepository.save(logData);
    }

    public void process(LogData logData) {
        // send to elasticsearch
        System.out.println("stored in db/elasticsearch");
        createLogIndex(logData);
    }
}
