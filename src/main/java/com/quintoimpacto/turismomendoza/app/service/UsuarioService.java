package com.quintoimpacto.turismomendoza.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quintoimpacto.turismomendoza.app.entity.Usuario;


@Service
public interface UsuarioService {
	
	public Iterable<Usuario> getAllUser(); 

	public List<Usuario> findAll();
	
	public void save(Usuario usuario);
	
	public Usuario findOne(Long id);
	
	public void delete(Long id);
}
