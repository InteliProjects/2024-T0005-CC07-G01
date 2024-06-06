package com.example.plano.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class for PJ mobile plans.
 */
@Entity
@Table(name = "plano")
public class PlanoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id_plano;

    private String nome;

    private Float valor;

    private String qtd_internet;

    public PlanoEntity() {
    }

    public int getId_plano() {
        return id_plano;
    }

    public void setId_plano(int id_plano) {
        this.id_plano = id_plano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getQtd_internet() {
        return qtd_internet;
    }

    public void setQtd_internet(String qtd_internet) {
        this.qtd_internet = qtd_internet;
    }

}
