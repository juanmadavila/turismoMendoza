package com.quintoimpacto.turismomendoza.app.models;

import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.enums.TipoDeEvento;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class EventoModel implements Serializable {

    private static final long serialVersionUID = 123456L;

    private String id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private Date fecha;
    private TipoDeEvento tipoDeEvento;
    private Usuario usuario;

}
