package br.edu.inteli.databus.entities;

import com.fasterxml.jackson.annotation.*;
import java.sql.Date;

/**
 * Private individual entity
 */
public class PessoaFisica {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String rg;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    /**
     * Get user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set user ID
     * 
     * @param id User ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get user name
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
     * Get user CPF
     */
    @JsonProperty("login")
    public String getCpf() {
        return cpf;
    }

    /**
     * Set user CPF
     * 
     * @param cpf User CPF
     */
    @JsonProperty("login")
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Get user RG
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
     * Get user birth date
     */
    @JsonProperty("data_nascimento")
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Set user birth date
     * 
     * @param dataNascimento User birth date
     */
    @JsonProperty("data_nascimento")
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Get user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user email
     * 
     * @param email User email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get user password
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
     * Constructor
     * 
     * @param id             User ID
     * @param nome           User name
     * @param cpf            User CPF
     * @param rg             User RG
     * @param dataNascimento User birth date
     */
    public PessoaFisica(Long id, String nome, String cpf, String rg, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }

    /**
     * Empty constructor
     */
    public PessoaFisica() {
    }
}
