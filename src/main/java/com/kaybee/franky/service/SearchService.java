package com.kaybee.franky.service;

import com.kaybee.franky.model.LogData;
import com.kaybee.franky.model.SearchCriteria;
import com.kaybee.franky.search.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private QueryBuilder queryBuilder;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public SearchPage<LogData> search(SearchCriteria criteria) {
        try {
            queryBuilder.createQuery(criteria);

        } catch (Exception e) {
            e.printStackTrace();
        }
        NativeSearchQuery search = queryBuilder.getSearch();

        try {
            SearchHits<LogData> searchHits = elasticsearchOperations.search(search, LogData.class);
            SearchPage<LogData> searchPage = SearchHitSupport.searchPageFor(
                    searchHits,
                    queryBuilder.getPageRequest()
            );

            return searchPage;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getRawJsonQuery(SearchCriteria criteria) {
        queryBuilder.createQuery(criteria);
        NativeSearchQuery search = queryBuilder.getSearch();

        return search.getQuery().toString();
    }
}
