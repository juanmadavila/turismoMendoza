package com.quintoimpacto.turismomendoza.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quintoimpacto.turismomendoza.app.dao.UsuarioRepositorio;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioRepo.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioRepo.save(usuario);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		usuarioRepo.deleteById(id);
	}

	@Override
	public Iterable<Usuario> getAllUser() {
		// TODO Auto-generated method stub
		return usuarioRepo.findAll();
	}
	

}
