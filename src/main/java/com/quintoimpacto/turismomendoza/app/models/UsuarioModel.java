package com.quintoimpacto.turismomendoza.app.models;

import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.enums.Rol;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class UsuarioModel implements Serializable {

    private static final long serialVersionUID = 123456L;

    private String id;
    private String name;
    private String email;
    private Boolean habilitado;
    private Date alta;
    private Rol rol;
    private List<EventoModel> eventosVisitados;

}
