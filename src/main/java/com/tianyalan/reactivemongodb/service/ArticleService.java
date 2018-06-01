package com.tianyalan.reactivemongodb.service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.tianyalan.reactivemongodb.domain.Article;
import com.tianyalan.reactivemongodb.repository.ArticleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Mono<Article> save(Article article) {
        return articleRepository.save(article);
    }

    public Mono<Article> findOne(String id) {
        return articleRepository.findById(id).log("findOneArticle");
    }

    public Flux<Article> findAll() {
        return articleRepository.findAll().log("findAll");
    }

    public Mono<Void> delete(String id) {
        return articleRepository.deleteById(id).log("deleteOneArticle");
    }
    
    public Flux<Article> findByAuthor(String author) {
    	Article e = new Article();
    	e.setAuthor("author");

    	ExampleMatcher matcher = ExampleMatcher.matching()
    		.withIgnoreCase()
    		.withMatcher("author", startsWith())
    		.withIncludeNullValues();

    	Example<Article> example = Example.of(e, matcher);
    	
    	Flux<Article> multipleArticles = articleRepository.findAll(example).log("findByAuthor");
    	
    	return multipleArticles;
    }
    
}
