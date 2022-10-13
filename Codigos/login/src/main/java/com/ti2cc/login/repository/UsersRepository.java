package com.ti2cc.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti2cc.login.model.UsersModel;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {
    Optional<UsersModel> findByLoginAndPassword(String login, String password);

    Optional<UsersModel> findFirstByLogin(String Login);
}