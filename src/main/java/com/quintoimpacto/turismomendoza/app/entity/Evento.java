package com.quintoimpacto.turismomendoza.app.entity;

import com.quintoimpacto.turismomendoza.app.enums.TipoDeEvento;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

//    @NotEmpty
    private String nombre;

//    @NotEmpty
    private String descripcion;

//    @NotEmpty
    private String ubicacion;

//    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fecha;
    
    @Enumerated(EnumType.STRING)
    private TipoDeEvento tipoDeEvento;
    
    @ManyToOne
    private Usuario usuario;
    
}
