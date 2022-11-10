package com.ti2cc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ti2cc.model.Comentario;
import com.ti2cc.model.Golpe;
import com.ti2cc.model.User;

public class ComentarioDAO extends DAO {
    public ComentarioDAO() {
        super();
        conectar();
    }

    public boolean insert(Comentario comentario) {
        boolean status = false;
        try {
            String sql = "INSERT INTO Comentario (Data, Texto, Score) "
                    + "VALUES ('"
                    + comentario.getData() + "', '" + comentario.getTexto() + "', '" + comentario.getScore() + "');";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            comentario.setId(getComentarioId(comentario.getTexto()));
            insertRelacion(comentario);
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean insertRelacion(Comentario comentario) {
        boolean status = false;
        try {
            String sql = "INSERT INTO Possui_Golpes_Comentario_Pessoa (fk_Golpes_Id, fk_Comentario_Id, fk_Pessoa_Id) "
                    + "VALUES ('"
                    + comentario.getGolpe().getId() + "', '" + comentario.getId() + "', '"
                    + comentario.getUser().getId() + "');";
            System.out.println(sql);
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Comentario get(Long id) {
        Comentario comentario = null;
        UserDAO userDAO = new UserDAO();
        GolpeDAO golpeDAO = new GolpeDAO();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Comentario WHERE Id=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                comentario = new Comentario(rs.getLong("Id"), rs.getDate("Data"), rs.getString("Texto"),
                        rs.getInt("Score"));
            }
            sql = "SELECT * FROM Possui_Golpes_Comentario_Pessoa WHERE fk_Comentario_Id=" + id;
            rs = st.executeQuery(sql);
            if (rs.next()) {
                comentario.setGolpe(golpeDAO.get(rs.getLong("fk_Golpes_Id")));
                comentario.setUser(userDAO.get(rs.getLong("fk_Pessoa_Id")));

            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return comentario;
    }

    public long getComentarioId(String text) {
        long resp = 0;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Comentario WHERE Texto LIKE '" + text + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                resp = rs.getLong("Id");
            }
        } catch (Exception e) {
            return 0;
        }
        return resp;
    }

    public Golpe getGolpeByRelacion(Long comentraioId) {
        Golpe golpe = null;
        GolpeDAO golpeDAO = new GolpeDAO();
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Possui_Golpes_Comentario_Pessoa WHERE fk_Comentario_Id=" + comentraioId;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                golpe = golpeDAO.get(rs.getLong("fk_Golpes_Id"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return golpe;
    }

    public User getUserByRelacion(Long comentraioId) {
        User user = null;
        UserDAO userDAO = new UserDAO();
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Possui_Golpes_Comentario_Pessoa WHERE fk_Comentario_Id=" + comentraioId;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                user = userDAO.get(rs.getLong("fk_Pessoa_Id"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public List<Comentario> get() {
        return get("");
    }

    public List<Comentario> getOrderByScore() {
        return get("Score");
    }

    private List<Comentario> get(String orderBy) {
        List<Comentario> comentarios = new ArrayList<Comentario>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Comentario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Comentario comentario = new Comentario(rs.getLong("Id"), rs.getDate("Data"), rs.getString("Texto"),
                        rs.getInt("Score"));
                comentario.setGolpe(getGolpeByRelacion(rs.getLong("Id")));
                comentario.setUser(getUserByRelacion(rs.getLong("Id")));
                comentarios.add(comentario);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return comentarios;
    }

    public int countGolpe(Long id) {
        int count = 0;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Possui_Golpes_Comentario_Pessoa WHERE fk_Golpes_Id=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                count++;
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    public boolean delete(Long id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM Comentario WHERE Id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
