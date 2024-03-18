package com.medico.vollMed.domains.usuario;

public enum Identificacao {
    USER("user"),
    WORKER("worker"),
    ADMIN("admin");

    private String role;

    Identificacao(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
