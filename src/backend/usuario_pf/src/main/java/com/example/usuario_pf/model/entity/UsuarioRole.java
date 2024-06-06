package com.example.usuario_pf.model.entity;


public enum UsuarioRole {
    ADMIN("admin"),
    USUARIOPF("usuario_pf"),
    USUARIOPJ("usuario_pj");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}