package com.kaybee.franky.search.filters;

import com.kaybee.franky.model.SearchCriteria;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class MessageFilter {
    public static AbstractQueryBuilder createFilter(SearchCriteria searchCriteria) {
        MatchQueryBuilder matchQueryFirst = QueryBuilders.matchQuery(
                        "message",
                        searchCriteria.getMessage()
                )
                .fuzziness(Fuzziness.TWO);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(matchQueryFirst);
        return boolQueryBuilder;
    }
}
