package com.quintoimpacto.turismomendoza.app.enums;

public enum Acceso {
    
    PUBLICO("Público"),
    PRIVADO("Privado");
    
    private String alias;
        
    private Acceso(String alias) {
        this.alias = alias;
    }
    
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
