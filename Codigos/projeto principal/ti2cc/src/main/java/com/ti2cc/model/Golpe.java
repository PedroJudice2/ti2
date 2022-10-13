package com.ti2cc.model;

import java.util.Objects;

public class Golpe {
    private Long id;
    private String nome;
    private String tipo;
    private String periculosidade;
    private String frequencia;

    public Golpe() {
    }

    public Golpe(Long id, String nome, String tipo, String periculosidade, String frequencia) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.periculosidade = periculosidade;
        this.frequencia = frequencia;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPericulosidade() {
        return this.periculosidade;
    }

    public void setPericulosidade(String periculosidade) {
        this.periculosidade = periculosidade;
    }

    public String getFrequencia() {
        return this.frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public Golpe id(Long id) {
        setId(id);
        return this;
    }

    public Golpe nome(String nome) {
        setNome(nome);
        return this;
    }

    public Golpe tipo(String tipo) {
        setTipo(tipo);
        return this;
    }

    public Golpe periculosidade(String periculosidade) {
        setPericulosidade(periculosidade);
        return this;
    }

    public Golpe frequencia(String frequencia) {
        setFrequencia(frequencia);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Golpe)) {
            return false;
        }
        Golpe golpe = (Golpe) o;
        return Objects.equals(id, golpe.id) && Objects.equals(nome, golpe.nome) && Objects.equals(tipo, golpe.tipo)
                && Objects.equals(periculosidade, golpe.periculosidade) && Objects.equals(frequencia, golpe.frequencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tipo, periculosidade, frequencia);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nome='" + getNome() + "'" +
                ", tipo='" + getTipo() + "'" +
                ", periculosidade='" + getPericulosidade() + "'" +
                ", frequencia='" + getFrequencia() + "'" +
                "}";
    }

}
