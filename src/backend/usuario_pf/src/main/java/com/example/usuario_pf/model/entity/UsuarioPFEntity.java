package com.example.usuario_pf.model.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

/**
 * Entity class that represents the 'usuario_pf' table in the database.
 * This class is used for ORM (Object-Relational Mapping) to map this class
 * to the database table with JPA (Jakarta Persistence API).
 */
@Entity
@Table(name = "usuario_pf")
public class UsuarioPFEntity implements UserDetails {

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
    public UsuarioPFEntity() {
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        if(this.role == UsuarioRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USUARIOPF"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USUARIOPF"));
        }
   
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return senha;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return login;
    }

}
