package com.example.usuario_pj.model.entity;

public enum UserRole {
    ADMIN("admin"),
    USUARIOPF("usuario_pf"),
    USUARIOPJ("usuario_pj");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}