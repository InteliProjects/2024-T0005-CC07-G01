package com.example.planos_usuarios.model.entity;

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
@Table(name = "planos_usuarios")
public class PlanosUsuariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int contrato;

    private int id_plano;

    private String qtd_internet_consumido;

    private String qtd_internet_restante;

    private Date data_inicio;

    private Date data_final;

    private Float fatura;

    private String telefone;

    private String status;

    private int id_endereco;

    private int id_usuario;
    

    public PlanosUsuariosEntity() {
    }


    public int getContrato() {
        return contrato;
    }


    public void setContrato(int contrato) {
        this.contrato = contrato;
    }


    public int getId_plano() {
        return id_plano;
    }


    public void setId_plano(int id_plano) {
        this.id_plano = id_plano;
    }


    public String getQtd_internet_consumido() {
        return qtd_internet_consumido;
    }


    public void setQtd_internet_consumido(String qtd_internet_consumido) {
        this.qtd_internet_consumido = qtd_internet_consumido;
    }


    public String getQtd_internet_restante() {
        return qtd_internet_restante;
    }


    public void setQtd_internet_restante(String qtd_internet_restante) {
        this.qtd_internet_restante = qtd_internet_restante;
    }


    public Date getData_inicio() {
        return data_inicio;
    }


    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }


    public Date getData_final() {
        return data_final;
    }


    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }


    public Float getFatura() {
        return fatura;
    }


    public void setFatura(Float fatura) {
        this.fatura = fatura;
    }


    public String getTelefone() {
        return telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public int getId_endereco() {
        return id_endereco;
    }


    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }


    public int getId_usuario() {
        return id_usuario;
    }


    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

   
}
