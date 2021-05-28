package com.quintoimpacto.turismomendoza.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quintoimpacto.turismomendoza.app.entity.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{

    
}