package com.ti2cc.app;

import static spark.Spark.*;

import com.ti2cc.service.ComentarioService;
import com.ti2cc.service.UserService;
import com.ti2cc.service.golpeService;

public class app {
    public static void main(String[] args) {
        port(6789);

        staticFiles.location("/public/Front-end");

        post("/insert", (request, response) -> golpeService.insert(request, response));
        post("/delete", (request, response) -> golpeService.delete(request, response));
        post("/update", (request, response) -> golpeService.update(request, response));

        // lambada for login

        post("/insertUser", (request, response) -> UserService.insert(request, response));
        post("/AuthUser", (request, response) -> UserService.autenticate(request, response));

        // lambada for comentario
        post("/insertGolpe", (request, response) -> ComentarioService.insert(request, response));
    }
}
