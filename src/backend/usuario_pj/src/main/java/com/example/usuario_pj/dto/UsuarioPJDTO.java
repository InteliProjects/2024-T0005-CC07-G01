package com.example.usuario_pj.dto;

import java.util.Date;

import com.example.usuario_pj.model.entity.UserRole;

import jakarta.persistence.*;

/**
 * Entity class that represents the 'usuario_pf' table in the database.
 * This class is used for ORM (Object-Relational Mapping) to map this class
 * to the database table with JPA (Jakarta Persistence API).
 */

public class UsuarioPJDTO {

    /**
     * Unique identifier of the 'Pessoa Jurídica' user. It is automatically
     * generated for each new entity object inserted into the database.
     */
    @Id
    private String login;



    /**
     * Identifier of the responsible person associated with this 'Pessoa Jurídica'
     * user.
     */
    private String razao_social;

    /**
     * The legal name of the 'Pessoa Jurídica' user.
     */
    private String senha;

    /**
     * The trade name of the 'Pessoa Jurídica' user's business.
     */
    private String data_nascimento;

    /**
     * The CNPJ (National Registry of Legal Entities) number of the 'Pessoa
     * Jurídica' user.
     */
    private int id_usuario;

    private UserRole role;


    /**
     * Default constructor.
     */
    public UsuarioPJDTO() {
    }

    

    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public String getRazao_social() {
        return razao_social;
    }


    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }


    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }


    public int getId_usuario() {
        return id_usuario;
    }


    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }



    public UserRole getRole() {
        return role;
    }



    public void setRole(UserRole role) {
        this.role = role;
    }

}
