package com.madplay.example.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.madplay.example.model.QArticle;
import com.madplay.example.model.QTeam;
import com.madplay.example.model.QUser;
import com.madplay.example.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<User> findAllByQuerydsl(Integer id, String name) {
		QUser user = QUser.user;
		QArticle article = QArticle.article;
		QTeam team = QTeam.team;

		return queryFactory.select(user)
			.from(user)
			.leftJoin(user.articleList, article)
			.fetchJoin()
			.leftJoin(user.team, team)
			.fetchJoin()
			.where(user.id.eq(id), user.name.eq(name))
			.distinct()
			.fetch();
	}

}
