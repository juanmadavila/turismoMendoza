package com.quintoimpacto.turismomendoza.app.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quintoimpacto.turismomendoza.app.entity.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long>{

    public Usuario findByNombre(String nombre);

	public Set<Usuario> findByUsername(String username);
    
}