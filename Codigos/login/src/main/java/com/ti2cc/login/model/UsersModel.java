package com.ti2cc.login.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pessoa")
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String login;

    String password;

    String email;

    Boolean status;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String Login) {
        this.login = Login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isStatus() {
        return this.status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus() {
        this.status = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsersModel)) {
            return false;
        }
        UsersModel usersModel = (UsersModel) o;
        return Objects.equals(id, usersModel.id) && Objects.equals(password, usersModel.password)
                && Objects.equals(password, usersModel.password) && Objects.equals(email, usersModel.email)
                && Objects.equals(status, usersModel.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, password, email, status);
    }

    @Override
    public String toString() {
        return "{" +
                " Id='" + getId() + "'" +
                ", password='" + getPassword() + "'" +
                ", email='" + getEmail() + "'" +
                ", status='" + isStatus() + "'" +
                "}";
    }

}
