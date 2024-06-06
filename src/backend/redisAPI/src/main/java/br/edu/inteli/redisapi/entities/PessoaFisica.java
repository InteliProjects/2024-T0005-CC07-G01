package br.edu.inteli.redisapi.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.fasterxml.jackson.annotation.*;

import jakarta.annotation.Nonnull;

/**
 * Private individual entity
 */
@RedisHash("PessoaFisica")
public class PessoaFisica {

    @Id
    @Nonnull
    private String id;
    @Indexed
    private String login;
    private String nome;
    private String rg;
    private String senha;
    private Date dataNascimento;

    /**
     * Get user login (CPF)
     * 
     * @return User login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set user login (CPF)
     * 
     * @param login User login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get user name
     * 
     * @return User name
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set user name
     * 
     * @param nome User name
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Get user RG
     * 
     * @return User RG
     */
    public String getRg() {
        return rg;
    }

    /**
     * Set user RG
     * 
     * @param rg User RG
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * Get user password
     * 
     * @return User password
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Set user password
     * 
     * @param senha User password
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Get user birth date
     * 
     * @return User birth date
     */
    @JsonProperty("data_nascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Set user birth date
     * 
     * @param dataNascimento User birth date
     */
    @JsonProperty("data_nascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Get user ID
     * 
     * @return User ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set user ID
     * 
     * @param id User ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Constructor
     * 
     * @param login          User CPF
     * @param nome           User name
     * @param rg             User RG
     * @param senha          User password
     * @param dataNascimento User birth date
     * @param id             User ID
     */
    public PessoaFisica(String login, String nome, String rg, String senha, Date dataNascimento, String id) {
        this.login = login;
        this.nome = nome;
        this.rg = rg;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.id = id;
    }

    /**
     * Empty constructor
     */
    public PessoaFisica() {
    }
}
