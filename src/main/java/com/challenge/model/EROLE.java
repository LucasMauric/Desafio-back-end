package com.challenge.model;

public enum EROLE {
    USER("user"),
    SHOPKEEPER("shopkeeper");
    private String role;

    EROLE(String role){
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }
}
