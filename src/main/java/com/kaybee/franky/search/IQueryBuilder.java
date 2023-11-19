package com.kaybee.franky.search;

import com.kaybee.franky.model.SearchCriteria;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

public interface IQueryBuilder {
    void createQuery(SearchCriteria searchCriteria);

    NativeSearchQuery getSearch();

}
