package com.ti2cc.service;

import java.sql.Date;

import com.ti2cc.dao.ComentarioDAO;
import com.ti2cc.dao.GolpeDAO;
import com.ti2cc.dao.UserDAO;
import com.ti2cc.model.Comentario;
import com.ti2cc.model.Golpe;
import com.ti2cc.model.User;

import spark.Request;
import spark.Response;
import spark.Session;

public class ComentarioService {

    public static ComentarioDAO comentarioDAO = new ComentarioDAO();

    public static String insert(Request request, Response response) {
        String resp = "";
        GolpeDAO golpeDAO = new GolpeDAO();
        UserDAO userDAO = new UserDAO();
        String nome = request.queryParams("nomeGolpe");
        String texto = request.queryParams("textoGolpe");
        long millis = System.currentTimeMillis();
        Date data = new Date(millis);

        if (!(golpeDAO.containsGolpe(nome))) {
            resp = "Golpe (" + nome + ") Ainda não fui incluso, contate o administrador do sistema ";
            response.status(500); // 201 Created
            return resp;
        }
        Session session = request.session(true);
        Long UserId = session.attribute("UserId");

        if (UserId == null) {
            resp = "<meta http-equiv= \"refresh\" content= \"0; url=/login.html\" />";
            response.status(500); // 201 Created
            return resp;
        }
        Long golpeId = golpeDAO.getGolpeId(nome);
        Golpe golpe = golpeDAO.get(golpeId);
        User user = userDAO.get(UserId);
        int count = comentarioDAO.countGolpe(golpeId);
        int score = hot(data, count);

        Comentario comentario = new Comentario(data, texto, golpe, user, score);

        if (comentarioDAO.insert(comentario)) {
            resp = "Comenario inserido! + ";
            response.status(201); // 201 Created
        } else {
            resp = "Comenario não inserido!";
            response.status(404); // 404 Not found
        }

        return resp;
    }

    public static int hot(Date date, int score) {
        int resp = 0;
        String str = "1970-01-01";
        Date epoch = Date.valueOf(str);
        Long diff = epoch.getTime() - date.getTime();
        int z;
        if (score > 0) {
            z = score;
        } else {
            z = 1;
        }
        resp = (int) (Math.log10(z) + (diff / 45000));
        resp = Math.abs(resp);
        System.out.println(resp);
        return resp;
    }
}
