package com.quintoimpacto.turismomendoza.app.service;

import com.quintoimpacto.turismomendoza.app.converters.UsuarioConverter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quintoimpacto.turismomendoza.app.dao.UsuarioRepositorio;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.enums.Rol;
import com.quintoimpacto.turismomendoza.app.models.UsuarioModel;
import java.util.ArrayList;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioService implements UserDetailsService{

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioConverter usuarioConverter;
    
    @Transactional
    public void save(Usuario usuario) {
        
        
        
        usuarioRepositorio.save(usuario);
    }

//    private void completar(UsuarioModel model) {
//        
//        model.setAlta(new Date());
//        model.setHabilitado(true);
//        model.setRol(Rol.USUARIO);
//        model.setMisEventos(new ArrayList<>());
//        model.setEventosVisitados(new ArrayList<>());
//        
//    }
    
    @Transactional
    public void delete(String id) {
        usuarioRepositorio.deleteById(id);
    }

    public Usuario findOne(String id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepositorio.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
