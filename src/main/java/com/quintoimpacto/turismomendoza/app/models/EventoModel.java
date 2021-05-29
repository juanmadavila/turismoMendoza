package com.quintoimpacto.turismomendoza.app.models;

import com.quintoimpacto.turismomendoza.app.enums.TipoDeEvento;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class EventoModel implements Serializable {

    private static final long serialVersionUID = 123456L;

    private String id;
    private String nombre;
    private String descripcion;
    private String lat;
    private String lon;
    private Date fecha;
    private Boolean habilitado;
    private TipoDeEvento tipoDeEvento;
    private UsuarioModel anfitrion;
    private List<UsuarioModel> visitantes;

}
