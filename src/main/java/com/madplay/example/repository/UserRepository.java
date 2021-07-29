package com.madplay.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madplay.example.model.User;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
}
