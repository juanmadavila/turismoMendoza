package com.quintoimpacto.turismomendoza.app.dao;

import org.springframework.stereotype.Repository;
import com.quintoimpacto.turismomendoza.app.entity.Evento;
import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, String> {
	
	 @Query("SELECT e FROM Evento e WHERE e.habilitado = true")
	 public List<Evento> findAllHabilitados();
	 
	 //    @Query("SELECT e FROM Evento e")
//    public Page<Evento> findAll(Pageable pageable);
//
//    @Query("SELECT e FROM Evento e WHERE e.nombre LIKE :nombre")
//    public Page<Evento> findAll(Pageable pageable, @Param("nombre") String nombre);

//    @Query("SELECT e FROM Evento e WHERE e.fecha LIKE :fecha")
//    public Page<Evento> findAllByFecha(Pageable pageable, @Param("fecha") Date fecha);

}
