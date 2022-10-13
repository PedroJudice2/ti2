package com.ti2cc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.ti2cc.model.Golpe;

public class GolpeDAO extends DAO {
    public GolpeDAO() {
        super();
        conectar();
    }

    public boolean insert(Golpe golpe) {
        boolean status = false;
        try {
            String sql = "INSERT INTO Golpes (Nome, Tipo, Periculosidade, Frequencia) "
                    + "VALUES ('" + golpe.getNome() + "', '"
                    + golpe.getTipo() + "', '" + golpe.getPericulosidade() + "', '" + golpe.getFrequencia() + "');";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Golpe get(Long id) {
        Golpe golpe = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Golpes WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                golpe = new Golpe(rs.getLong("Id"), rs.getString("Nome"), rs.getString("Tipo"),
                        rs.getString("Periculosidade"), rs.getString("Periculosidade"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return golpe;
    }

    public List<Golpe> get() {
        return get("");
    }

    public List<Golpe> getOrderByID() {
        return get("id");
    }

    public List<Golpe> getOrderByDescricao() {
        return get("descricao");
    }

    public List<Golpe> getOrderByPreco() {
        return get("preco");
    }

    private List<Golpe> get(String orderBy) {
        List<Golpe> golpes = new ArrayList<Golpe>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Golpes" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Golpe p = new Golpe(rs.getLong("Id"), rs.getString("Nome"), rs.getString("Tipo"),
                        rs.getString("Periculosidade"), rs.getString("Periculosidade"));
                golpes.add(p);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return golpes;
    }

    public boolean update(Golpe golpe) {
        boolean status = false;
        try {
            String sql = "UPDATE Golpes SET Nome = '" + golpe.getNome() + "', "
                    + "Tipo = '" + golpe.getTipo() + "', "
                    + "Periculosidade = '" + golpe.getPericulosidade() + "',"
                    + "Frequencia = '" + golpe.getFrequencia() + "'";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean delete(Long id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM Golpes WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
