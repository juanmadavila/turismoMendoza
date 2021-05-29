package com.quintoimpacto.turismomendoza.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{

    @Query("SELECT u FROM Usuario u WHERE u.email LIKE :email")
    public Usuario findByEmail(@Param("email") String email);
    
}