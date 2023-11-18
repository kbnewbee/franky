package com.kaybee.franky.processor;

import com.kaybee.franky.model.LogData;
import org.springframework.stereotype.Service;

@Service
public class LogProcessor {

    public void process(LogData logData) {
        //store in mongodb
        //send to elasticsearch
        System.out.println("stored in db/elasticsearch");
    }

}
