package com.madplay.example.repository;

import java.util.List;

import com.madplay.example.model.Article;

/**
 * Querydsl로 작성할 쿼리는 이 곳에 시그니처를 선언하고 `~RepositoryImpl`에서 구현한다.
 */
public interface ArticleRepositoryCustom {
	List<Article> findByLevelUsingQuerydsl(String level);

	List<Article> searchArticle(String title, Integer userId);
}
