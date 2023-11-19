package com.kaybee.franky.processor;

import com.kaybee.franky.model.LogData;
import com.kaybee.franky.repository.LogElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryProcessor {

    @Autowired
    private LogElasticRepository logElasticRepository;

    public List<LogData> findAllByLevel(final String level) {
        return logElasticRepository.findAllByLevel(level);
    }

    public List<LogData> findALlByResourceId(String resourceId) {
        return logElasticRepository.findALlByResourceId(resourceId);
    }

    public LogData findByTraceId(String traceId) {
        return logElasticRepository.findByTraceId(traceId);
    }

    public LogData findBySpanId(String spanId) {
        return logElasticRepository.findBySpanId(spanId);
    }

    public LogData findByCommit(String commit) {
        return logElasticRepository.findByCommit(commit);
    }
}
