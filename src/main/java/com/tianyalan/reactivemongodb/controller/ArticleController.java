package com.tianyalan.reactivemongodb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tianyalan.reactivemongodb.domain.Article;
import com.tianyalan.reactivemongodb.service.ArticleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    public Mono<ResponseEntity<Article>> create(@RequestBody Article article) {
        return articleService.save(article).map(ResponseEntity::ok);
    }

    @PutMapping("/article")
    public Mono<ResponseEntity<Article>> update(@RequestBody Article article) {
        return articleService.save(article).map(ResponseEntity::ok);
    }

    @GetMapping("/article")
    public Flux<ResponseEntity<Article>> list() {
        return articleService.findAll().map(ResponseEntity::ok);
    }

    @GetMapping("/article/{id}")
    public Mono<ResponseEntity<Article>> findById(@PathVariable String id) {
        return articleService.findOne(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(404).body(null));
    }
    
    @DeleteMapping("/article/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {
        return articleService.delete(id).map(ResponseEntity::ok);
    }
    
    @GetMapping("/article/author/{author}")
    public Flux<Article> findByAuthor(@PathVariable String author) {
        return articleService.findByAuthor(author).log("findByAuthor");
    }
}
