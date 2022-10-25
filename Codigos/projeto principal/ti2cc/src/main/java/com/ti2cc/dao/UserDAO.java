package com.ti2cc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ti2cc.model.User;

public class UserDAO extends DAO {
    public UserDAO() {
        super();
        conectar();
    }

    public boolean insert(User user) {
        boolean status = false;
        try {
            String sql = "INSERT INTO Pessoa (Login, Email, Password, Status) "
                    + "VALUES ('"
                    + user.getLogin() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', "
                    + user.getStatus() + ");";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public User get(Long id) {
        User user = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Golpes WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                user = new User(rs.getLong("Id"), rs.getString("Login"), rs.getString("Email"),
                        rs.getString("Password"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public boolean autenticate(String login, String password) {
        boolean resp = false;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Pessoa WHERE login LIKE '" + login + "' AND password LIKE '" + password + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            resp = rs.next();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resp;
    }

}
