package com.kaybee.franky.search.filters;

import com.kaybee.franky.model.SearchCriteria;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

public class TimestampRangeFilter {

    public static AbstractQueryBuilder createFilter(SearchCriteria searchCriteria) {
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("timestamp")
                .gte(searchCriteria.getStartDate())
                .lte(searchCriteria.getEndDate());

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(rangeQueryBuilder);
        return boolQueryBuilder;
    }

}
