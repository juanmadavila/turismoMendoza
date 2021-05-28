package com.quintoimpacto.turismomendoza.app.enums;

public enum TipoDeEvento {

    CULTURAL("Cultural"),
    RESTO("Restó"),
    FIESTA("Fiesta");
    
    private String alias;

    private TipoDeEvento(String alias) {
        this.alias = alias;
    }
    
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
