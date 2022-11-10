package com.ti2cc.model;

import java.sql.Date;
import java.util.Objects;

public class Comentario {
    private Long id;
    private Date data;
    private String texto;
    private Golpe golpe;
    private User user;
    private int score;

    public Comentario() {
    }

    public Comentario(Long id, Date data, String texto, Golpe golpe, User user, int score) {
        this.id = id;
        this.data = data;
        this.texto = texto;
        this.golpe = golpe;
        this.user = user;
        this.score = score;
    }

    public Comentario(Date data, String texto, Golpe golpe, User user, int score) {
        this.data = data;
        this.texto = texto;
        this.golpe = golpe;
        this.user = user;
        this.score = score;
    }

    public Comentario(Long id, Date data, String texto, int score) {
        this.id = id;
        this.data = data;
        this.texto = texto;
        this.score = score;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Golpe getGolpe() {
        return this.golpe;
    }

    public void setGolpe(Golpe golpe) {
        this.golpe = golpe;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Comentario id(Long id) {
        setId(id);
        return this;
    }

    public Comentario data(Date data) {
        setData(data);
        return this;
    }

    public Comentario texto(String texto) {
        setTexto(texto);
        return this;
    }

    public Comentario golpe(Golpe golpe) {
        setGolpe(golpe);
        return this;
    }

    public Comentario user(User user) {
        setUser(user);
        return this;
    }

    public Comentario score(int score) {
        setScore(score);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Comentario)) {
            return false;
        }
        Comentario comentario = (Comentario) o;
        return Objects.equals(id, comentario.id) && Objects.equals(data, comentario.data)
                && Objects.equals(texto, comentario.texto) && Objects.equals(golpe, comentario.golpe)
                && Objects.equals(user, comentario.user) && Objects.equals(score, comentario.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, texto, golpe, user, score);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", data='" + getData() + "'" +
                ", texto='" + getTexto() + "'" +
                ", golpe='" + getGolpe() + "'" +
                ", user='" + getUser() + "'" +
                ", score='" + getScore() + "'" +
                "}";
    }

}
