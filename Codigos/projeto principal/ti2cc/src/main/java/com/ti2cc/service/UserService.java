package com.ti2cc.service;

import java.math.BigInteger;
import java.security.MessageDigest;

import com.ti2cc.dao.UserDAO;
import com.ti2cc.model.User;

import spark.Request;
import spark.Response;
import spark.Session;

public class UserService {
    private static UserDAO userDAO = new UserDAO();

    public static String insert(Request request, Response response) throws Exception {
        String login = request.queryParams("login");
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        String resp = "";

        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(toMD5(password));
        user.setStatus();

        if (userDAO.insert(user) == true) {
            resp = "<meta http-equiv= \"refresh\" content= \"0; url=/login.html\" />";
            response.status(201); // 201 Created
        } else {
            resp = "Usuario (" + login + ") não inserido!";
            response.status(404); // 404 Not found
        }

        return resp;
    }

    public static String autenticate(Request request, Response response) throws Exception {
        String login = request.queryParams("login");
        String password = toMD5(request.queryParams("password"));

        String resp = "";

        if (userDAO.autenticate(login, password)) {
            User user = userDAO.get(login);
            // create session
            Session session = request.session(true);
            session.attribute("UserId", user.getId());
            Long teste = session.attribute("UserId");
            System.out.println(teste);
            request.attribute("nomeUser", teste);
            if ((user.getStatus())) {
                resp = "<meta http-equiv= \"refresh\" content= \"0; url=/indexADM.html\" />";
            } else {
                resp = "<meta http-equiv= \"refresh\" content= \"0; url=/indexNormal.html\" />";
            }

            response.status(201); // 201 Created
        } else {
            resp = "Golpe (" + login + ") não logado";
            response.status(404); // 404 Not found
        }

        return resp;
    }

    public static String toMD5(String senha) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
