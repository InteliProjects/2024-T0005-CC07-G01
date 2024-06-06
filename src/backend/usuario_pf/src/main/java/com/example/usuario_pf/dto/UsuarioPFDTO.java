package com.example.usuario_pf.dto;

import java.util.Date;

import com.example.usuario_pf.model.entity.UsuarioRole;

import jakarta.persistence.*;

/**
 * Entity class that represents the 'usuario_pf' table in the database.
 * This class is used for ORM (Object-Relational Mapping) to map this class
 * to the database table with JPA (Jakarta Persistence API).
 */

public class UsuarioPFDTO {

    /**
     * Unique identifier of the 'Pessoa Jurídica' user. It is automatically
     * generated for each new entity object inserted into the database.
     */
    private String login;

    /**
     * Identifier of the responsible person associated with this 'Pessoa Jurídica'
     * user.
     */
    private String nome;

    /**
     * The legal name of the 'Pessoa Jurídica' user.
     */
    private String rg;

    /**
     * The trade name of the 'Pessoa Jurídica' user's business.
     */
    private String senha;

    /**
     * The CNPJ (National Registry of Legal Entities) number of the 'Pessoa
     * Jurídica' user.
     */
    private String data_nascimento;

    /**
     * The email address of the 'Pessoa Jurídica' user.
     */

    /**
     * The date when the 'Pessoa Jurídica' user's information was registered.
     */
    private int id_usuario;


    private UsuarioRole role;


    /**
     * Default constructor.
     */
    public UsuarioPFDTO() {
    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getRg() {
        return rg;
    }


    public void setRg(String rg) {
        this.rg = rg;
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


    public UsuarioRole getRole() {
        return role;
    }


    public void setRole(UsuarioRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UsuarioPFDTO [data_nascimento=" + data_nascimento + ", id_usuario=" + id_usuario + ", login=" + login
                + ", nome=" + nome + ", rg=" + rg + ", role=" + role + ", senha=" + senha + "]";
    }
    

}
