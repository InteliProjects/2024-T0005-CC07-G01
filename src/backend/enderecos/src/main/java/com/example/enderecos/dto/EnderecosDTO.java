/**
 * This package contains the Data Transfer Object (DTO) for the Enderecos application.
 */
package com.example.enderecos.dto;

/**
 * This class represents the DTO for the Enderecos.
 */
public class EnderecosDTO {

    private int id_endereco;
    private int id_usuario;
    private String rua;
    private String cep;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;

    /**
     * Default constructor for EnderecosDTO.
     */
    public EnderecosDTO() {
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    

}