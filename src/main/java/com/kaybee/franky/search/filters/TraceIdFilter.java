package com.kaybee.franky.search.filters;

import com.kaybee.franky.model.SearchCriteria;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;

public class TraceIdFilter {

    public static AbstractQueryBuilder createFilter(SearchCriteria searchCriteria) {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(
                "trace_id",
                searchCriteria.getTraceId()
        );

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(termQueryBuilder);
        return boolQueryBuilder;
    }
}
