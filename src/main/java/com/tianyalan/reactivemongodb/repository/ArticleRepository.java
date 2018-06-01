package com.tianyalan.reactivemongodb.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.tianyalan.reactivemongodb.domain.Article;

@Repository
public interface ArticleRepository
		extends ReactiveMongoRepository<Article, String>, ReactiveQueryByExampleExecutor<Article> {
}
