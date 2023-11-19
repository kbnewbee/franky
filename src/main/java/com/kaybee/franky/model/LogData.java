package com.kaybee.franky.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

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

    Personal note:
    The primary difference between the text datatype and the keyword datatype is that text fields are analyzed
    at the time of indexing, and keyword fields are not. What that means is, text fields are broken down into their
    individual terms at indexing to allow for partial matching, while keyword fields are indexed as is.
}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "log_index")
public class LogData {

    @Id
    @Field(type = FieldType.Keyword, name = "trace_id")
    private String traceId;

    @Field(type = FieldType.Keyword, name = "level")
    private String level;

    @Field(type = FieldType.Text, name = "message")
    private String message;

    @Field(type = FieldType.Keyword, name = "resource_id")
    private String resourceId;

    @Field(type = FieldType.Date, format = {}, name = "timestamp")
    private LocalDateTime timestamp;

    @Field(type = FieldType.Keyword, name = "span_id")
    private String spanId;

    @Field(type = FieldType.Keyword, name = "commit")
    private String commit;

    @Field(type = FieldType.Nested, name = "metadata")
    private Metadata metadata;

}
