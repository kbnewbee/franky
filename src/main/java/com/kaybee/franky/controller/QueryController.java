package com.kaybee.franky.controller;

import com.kaybee.franky.model.LogData;
import com.kaybee.franky.model.SearchCriteria;
import com.kaybee.franky.processor.QueryProcessor;
import com.kaybee.franky.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QueryController {

    @Autowired
    private QueryProcessor queryProcessor;

    @Autowired
    private SearchService searchService;

    @GetMapping("/query")
    public ResponseEntity<List<LogData>> findAllErrors(@RequestParam String error) {
        List<LogData> response = queryProcessor.findAllByLevel(error);
        if (!response.isEmpty()) {
            System.out.println(response.size());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @PostMapping("/search")
    public ResponseEntity<List<LogData>> search(@Validated @RequestBody SearchCriteria searchCriteria) {
        try {

            SearchPage<LogData> searchPage = searchService.search(searchCriteria);

            Iterator<SearchHit<LogData>> iterator = searchPage.iterator();
            List<LogData> response = new ArrayList<>();

            while (iterator.hasNext()) {
                LogData logData = iterator.next().getContent();
                response.add(logData);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
