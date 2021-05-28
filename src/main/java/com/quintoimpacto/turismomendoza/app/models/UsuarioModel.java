package com.quintoimpacto.turismomendoza.app.models;

import com.quintoimpacto.turismomendoza.app.enums.Rol;
import java.io.Serializable;
import lombok.Data;

@Data
public class UsuarioModel implements Serializable {
    
    private static final long serialVersionUID = 123456L;
    
    private String id;
    private String name;
    private String email;
    private Rol rol;
    
}
