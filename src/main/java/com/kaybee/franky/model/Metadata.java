package com.kaybee.franky.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Metadata {

    @Field(type = FieldType.Keyword, name = "parent_resource_id")
    private String parentResourceId;
}
