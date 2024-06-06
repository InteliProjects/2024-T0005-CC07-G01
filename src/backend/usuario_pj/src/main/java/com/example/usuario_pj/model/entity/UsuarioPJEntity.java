package com.example.usuario_pj.model.entity;

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
@Table(name = "usuario_pj")
public class UsuarioPJEntity implements UserDetails {

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
    public UsuarioPJEntity() {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USUARIOPJ"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USUARIOPJ"));
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
