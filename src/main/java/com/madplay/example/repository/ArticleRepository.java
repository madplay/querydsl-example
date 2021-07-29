package com.madplay.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.madplay.example.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>, ArticleRepositoryCustom {
	@Query(value = "SELECT id, title, user_id FROM article WHERE user_id IN (SELECT id FROM user WHERE level > :level)", nativeQuery = true)
	List<Article> findByLevel(String level);
}
