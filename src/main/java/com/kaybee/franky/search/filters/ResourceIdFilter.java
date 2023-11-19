package com.kaybee.franky.search.filters;

import com.kaybee.franky.model.SearchCriteria;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;

public class ResourceIdFilter {

    public static AbstractQueryBuilder createFilter(SearchCriteria searchCriteria) {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(
                "resource_id",
                searchCriteria.getResourceId()
        );

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(termQueryBuilder);
        return boolQueryBuilder;
    }
}
