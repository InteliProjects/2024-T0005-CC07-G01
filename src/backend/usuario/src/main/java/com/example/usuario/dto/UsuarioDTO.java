package com.example.usuario.dto;

import java.util.Date;
import jakarta.persistence.*;

/**
 * Entity class that represents the 'usuario' table in the database.
 * This class is used for ORM (Object-Relational Mapping) to map this class
 * to the database table with JPA (Jakarta Persistence API).
 */

public class UsuarioDTO {

    private int id_usuario;

    /**
     * Identifier of the responsible person associated with this 'Pessoa Jurídica'
     * user.
     */
    private String tipo;

    /**
     * The legal name of the 'Pessoa Jurídica' user.
     */
    private String nome;

    /**
     * The trade name of the 'Pessoa Jurídica' user's business.
     */
    private String email;

    /**
     * The CNPJ (National Registry of Legal Entities) number of the 'Pessoa
     * Jurídica' user.
     */
    private String saldo;

    /**
     * The email address of the 'Pessoa Jurídica' user.
     */

    /**
     * The date when the 'Pessoa Jurídica' user's information was registered.
     */
    private Date data_cadastro;

    /**
     * Default constructor.
     */
    public UsuarioDTO() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

}
