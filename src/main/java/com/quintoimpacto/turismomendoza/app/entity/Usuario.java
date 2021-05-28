package com.quintoimpacto.turismomendoza.app.entity;


import com.quintoimpacto.turismomendoza.app.enums.Rol;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;
    private String email;
    private Boolean habilitado;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    @OneToMany
    private List<Evento> misEventos;

    @OneToMany
    private List<Evento> eventosVisitados;
    
}
