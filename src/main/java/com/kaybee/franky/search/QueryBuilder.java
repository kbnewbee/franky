package com.kaybee.franky.search;

import com.kaybee.franky.model.SearchCriteria;
import com.kaybee.franky.search.filters.*;
import lombok.Getter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
public class QueryBuilder implements IQueryBuilder {

    private final NativeSearchQueryBuilder searchQueryBuilder;

    @Getter
    private PageRequest pageRequest;

    public QueryBuilder() {
        this.searchQueryBuilder = new NativeSearchQueryBuilder();
    }

    @Override
    public void createQuery(SearchCriteria criteria) {
        //this.setPageOffset(criteria);
        this.setFilters(criteria);
        this.setSorting(criteria);
        this.setFields(criteria);
    }

    @Override
    public NativeSearchQuery getSearch() {
        return this.searchQueryBuilder.build();
    }

    protected void setFilters(SearchCriteria criteria) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (criteria.getLevel() != null) {
            boolQueryBuilder.must(ErrorLevelFilter.createFilter(criteria));
        }

        if (criteria.getMessage()!= null) {
            boolQueryBuilder.must(MessageFilter.createFilter(criteria));
        }

        if (criteria.getResourceId()!= null) {
            boolQueryBuilder.must(ResourceIdFilter.createFilter(criteria));
        }

        if (criteria.getTraceId()!= null) {
            boolQueryBuilder.must(TraceIdFilter.createFilter(criteria));
        }

        if (criteria.getSpanId()!= null) {
            boolQueryBuilder.must(SpanIdFilter.createFilter(criteria));
        }

        if (criteria.getCommit()!= null) {
            boolQueryBuilder.must(CommitFilter.createFilter(criteria));
        }

        if (criteria.getMetadata() != null && criteria.getMetadata().getParentResourceId() != null) {
            boolQueryBuilder.must(MetadataFilter.createFilter(criteria));
        }

        if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
            boolQueryBuilder.must(TimestampRangeFilter.createFilter(criteria));
        }

        this.searchQueryBuilder.withQuery(boolQueryBuilder);
    }

    protected void setPageOffset(SearchCriteria criteria) {
        this.pageRequest = PageRequest.of(criteria.getPage(), criteria.getSize());
        this.searchQueryBuilder.withPageable(this.pageRequest);
    }

    protected void setFields(SearchCriteria criteria) {
        this.searchQueryBuilder.withFields(
                "level",
                "message",
                "resource_id",
                "span_id",
                "trace_id",
                "commit",
                "metadata"
        );
    }

    protected void setSorting(SearchCriteria criteria) {
        this.searchQueryBuilder.withSorts(SortBuilders
                .fieldSort("timestamp")
                .order(SortOrder.ASC)
        );
    }
}
