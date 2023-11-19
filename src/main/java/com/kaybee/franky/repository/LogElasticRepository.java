package com.kaybee.franky.repository;

import com.kaybee.franky.model.LogData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/*
{
	"level": "error",
	"message": "Failed to connect to DB",
    "resourceId": "server-1234",
	"timestamp": "2023-09-15T08:00:00Z",
	"traceId": "abc-xyz-123",
    "spanId": "span-456",
    "commit": "5e5342f",
    "metadata": {
        "parentResourceId": "server-0987"
    }
}
 */
public interface LogElasticRepository extends ElasticsearchRepository<LogData, String> {
    List<LogData> findAllByLevel(String level);

    List<LogData> findALlByResourceId(String resourceId);

    LogData findByTraceId(String traceId);

    LogData findBySpanId(String spanId);

    LogData findByCommit(String commit);

}
