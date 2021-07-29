package com.madplay.example;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import com.madplay.example.model.Article;
import com.madplay.example.model.User;
import com.madplay.example.repository.ArticleRepository;
import com.madplay.example.repository.UserRepository;

@SpringBootTest
class ExampleApplicationTests {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private UserRepository userRepository;

	@Test
	void testGetArticleList() {
		// Native Query
		List<Article> articleList = articleRepository.findByLevel("1");

		System.out.println("--------------------------------------------------");

		// Querydsl
		List<Article> articleListByQuerydsl = articleRepository.findByLevelUsingQuerydsl("1");

		Assertions.assertEquals(articleList.size(), articleListByQuerydsl.size());
	}

	@Test
	void testSearchArticleList() {
		List<Article> articleList = articleRepository.searchArticle("", 1);
		System.out.println("size: " + articleList.size());
	}

	@Test
	void testGetUser() {
		Example<User> matcher = Example.of(User.builder().id(1).name("taeng").build());
		List<User> userList = userRepository.findAll(matcher);

		System.out.println("--------------------------------------------------");

		List<User> userListByQuerydsl = userRepository.findAllByQuerydsl(1, "taeng");

		Assertions.assertEquals(userList.size(), userListByQuerydsl.size());
	}
}
