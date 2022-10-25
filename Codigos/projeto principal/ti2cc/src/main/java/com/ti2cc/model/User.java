package com.ti2cc.model;

import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String email;
    private String password;
    private boolean status;

    public User() {
    }

    public User(Long id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.status = false;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus() {
        this.status = false;
    }

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User login(String login) {
        setLogin(login);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User status(boolean status) {
        setStatus();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password) && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, password, status);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", login='" + getLogin() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", status='" + isStatus() + "'" +
                "}";
    }

}
