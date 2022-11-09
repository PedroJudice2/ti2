package com.ti2cc.service;

import com.ti2cc.dao.GolpeDAO;
import com.ti2cc.model.Golpe;

import spark.Request;
import spark.Response;

public class golpeService {

    private static GolpeDAO golpeDAO = new GolpeDAO();

    public static String insert(Request request, Response response) {
        String nome = request.queryParams("nomeGolpe");
        String tipo = request.queryParams("tipoGolpe");
        String periculosidade = request.queryParams("periculosidade");
        String frequencia = request.queryParams("frequencia");

        String resp = "";

        Golpe golpe = new Golpe();
        golpe.setNome(nome);
        golpe.setTipo(tipo);
        golpe.setPericulosidade(periculosidade);
        golpe.setFrequencia(frequencia);

        if (golpeDAO.insert(golpe) == true) {
            resp = "Golpe (" + nome + ") inserido! + ";
            response.status(201); // 201 Created
        } else {
            resp = "Golpe (" + nome + ") não inserido!";
            response.status(404); // 404 Not found
        }

        return resp;
    }

    public static Object delete(Request request, Response response) {
        long id = Long.parseLong(request.queryParams("idGolpe"));
        Golpe golpe = golpeDAO.get(id);
        String resp = "";

        if (golpe != null) {
            golpeDAO.delete(id);
            response.status(200); // success
            resp = "Golpe (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Golpe (" + id + ") não encontrado!";
        }
        return resp;
    }

    public static Object update(Request request, Response response) {
        long id = Long.parseLong(request.queryParams("idGolpe"));
        Golpe golpe = golpeDAO.get(id);
        String resp = "";

        if (golpe != null) {
            golpe.setNome(request.queryParams("nomeGolpe"));
            golpe.setTipo(request.queryParams("tipoGolpe"));
            golpe.setPericulosidade(request.queryParams("periculosidade"));
            golpe.setFrequencia(request.queryParams("frequencia"));
            golpeDAO.update(golpe);
            response.status(200); // success
            resp = "Golpe (ID " + golpe.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Golpe (ID " + id + ") não encontrado!";
        }
        return resp;
    }
}
