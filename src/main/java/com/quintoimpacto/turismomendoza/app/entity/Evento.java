package com.quintoimpacto.turismomendoza.app.entity;

import com.quintoimpacto.turismomendoza.app.enums.Acceso;
import com.quintoimpacto.turismomendoza.app.enums.TipoDeEvento;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private String descripcion;
    private String lat;
    private String lon;
    private String horario;
    private Boolean habilitado;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fecha;
    
    @Enumerated(EnumType.STRING)
    private TipoDeEvento tipoDeEvento;
    
    @Enumerated(EnumType.STRING)
    private Acceso acceso;
    
    @ManyToOne
    private Usuario anfitrion;
    
    @ManyToMany
    private List<Usuario> visitantes;
    
    @ManyToMany
    private List<Usuario> pendientes;
    
}
