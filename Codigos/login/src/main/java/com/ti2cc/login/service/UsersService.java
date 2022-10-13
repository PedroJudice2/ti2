package com.ti2cc.login.service;

import org.springframework.stereotype.Service;

import com.ti2cc.login.model.UsersModel;
import com.ti2cc.login.repository.UsersRepository;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email) {
        if (login != null && password != null) {
            if (usersRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("Usuario ja cadastrado");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            usersModel.setStatus();
            return usersRepository.save(usersModel);
        } else {
            return null;
        }
    }

    public UsersModel authenticate(String login, String password) {
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}