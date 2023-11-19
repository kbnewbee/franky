package com.kaybee.franky.search.filters;

import com.kaybee.franky.model.SearchCriteria;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;

public class CommitFilter {

    public static AbstractQueryBuilder createFilter(SearchCriteria searchCriteria) {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(
                "commit",
                searchCriteria.getCommit()
        );

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(termQueryBuilder);
        return boolQueryBuilder;
    }
}
