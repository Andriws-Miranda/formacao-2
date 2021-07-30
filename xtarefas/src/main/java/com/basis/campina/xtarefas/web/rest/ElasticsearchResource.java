package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.service.elastic.ElasticsearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elastic")
@RequiredArgsConstructor
public class ElasticsearchResource {

    private final ElasticsearchService elasticSearchService;

    @GetMapping
    public ResponseEntity<String> reindex() {
        this.elasticSearchService.reindex();
        return ResponseEntity.ok("Reindexando todo o elastic");
    }

    @GetMapping("/{entity}")
    public ResponseEntity<String> reindexEntity(@RequestParam String entity) {
        elasticSearchService.reindexEntity(entity);
        return ResponseEntity.ok().build();
    }
}
