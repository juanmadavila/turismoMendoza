package com.quintoimpacto.turismomendoza.app.dao;

import org.springframework.stereotype.Repository;
import com.quintoimpacto.turismomendoza.app.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, String>{
    
}
