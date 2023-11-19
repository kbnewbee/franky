package com.kaybee.franky.search.filters;

import com.kaybee.franky.model.SearchCriteria;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;

public class MetadataFilter {

    public static AbstractQueryBuilder createFilter(SearchCriteria searchCriteria) {

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(
                "metadata.parent_resource_id",
                searchCriteria.getMetadata().getParentResourceId()
        );

        NestedQueryBuilder metadataQueryBuilder = QueryBuilders.nestedQuery("metadata", termQueryBuilder, ScoreMode.None);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(metadataQueryBuilder);
        return boolQueryBuilder;
    }
}
