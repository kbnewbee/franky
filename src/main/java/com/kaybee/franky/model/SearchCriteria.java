package com.kaybee.franky.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchCriteria {
    private String level;
    private String message;
    private String resourceId;

    @Field(type = FieldType.Date, format = {})
    private LocalDateTime startDate;

    @Field(type = FieldType.Date, format = {})
    private LocalDateTime endDate;

    private String traceId;
    private String spanId;
    private String commit;
    private Metadata metadata;
    private int page;
    private int size;
}
