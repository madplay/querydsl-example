package com.madplay.example.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.madplay.example.model.Article;
import com.madplay.example.model.QArticle;
import com.madplay.example.model.QUser;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	private QArticle article = QArticle.article;
	private QUser user = QUser.user;

	public List<Article> findByLevelUsingQuerydsl(String level) {

		return queryFactory.selectFrom(article)
			.where(
				article.userId.in(
					JPAExpressions
						.select(user.id)
						.from(user)
						.where(user.level.gt(level))
				)
			)
			.fetch();
	}

	public List<Article> searchArticle(String title, Integer userId) {
		return queryFactory.selectFrom(article)
			.where(titleContains(title), userIdEq(userId))
			.fetch();
	}

	private BooleanExpression titleContains(String title) {
		return StringUtils.isNotBlank(title) ? article.title.contains(title) : null;
	}

	private BooleanExpression userIdEq(Integer userId) {
		return userId != null ? article.userId.eq(userId) : null;
	}
}
