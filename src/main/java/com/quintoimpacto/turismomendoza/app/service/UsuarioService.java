package com.quintoimpacto.turismomendoza.app.service;

import com.quintoimpacto.turismomendoza.app.converters.UsuarioConverter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quintoimpacto.turismomendoza.app.dao.UsuarioRepositorio;
import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.enums.Rol;
import com.quintoimpacto.turismomendoza.app.models.UsuarioModel;
import com.quintoimpacto.turismomendoza.app.oauth.CustomOauth2User;
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
    private final EventoService eventoService;
    
    @Transactional
    public void save(CustomOauth2User oauth2User) {
        
        Usuario usuario = new Usuario();
        usuario.setEmail(oauth2User.getEmail());
        usuario.setName(oauth2User.getName());
        usuario.setPhotoUrl(oauth2User.getPhoto());
        usuario.setRol(Rol.USUARIO);
        usuario.setAlta(new Date());
        usuario.setHabilitado(true);
        usuario.setEventos(new ArrayList<>());
        
        usuarioRepositorio.save(usuario);
    }
    @Transactional
    public void save(Usuario usuario) {      
        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void delete(String id) {
        usuarioRepositorio.deleteById(id);
    }
    
    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }  

    public Usuario findOne(String id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepositorio.findAll();
    }
    @Transactional
    public void participar(String idEvento, Usuario usuario) {
        Evento evento = eventoService.findById(idEvento);
        
        for(Usuario usuarioVisitante: evento.getVisitantes()) {
        	if(usuarioVisitante.getId().equals(usuario.getId()) ) {
        		return;
        	}
        }
        evento.getVisitantes().add(usuario); 
        usuario.getEventos().add(evento);
        
        usuarioRepositorio.saveAndFlush(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
