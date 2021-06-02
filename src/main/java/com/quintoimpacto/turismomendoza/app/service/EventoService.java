package com.quintoimpacto.turismomendoza.app.service;

import com.quintoimpacto.turismomendoza.app.converters.EventoConverter;
import com.quintoimpacto.turismomendoza.app.dao.EventoRepositorio;
import com.quintoimpacto.turismomendoza.app.dao.UsuarioRepositorio;
import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.EventoModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventoService {

    private final EventoRepositorio eventoRepositorio;
    private final EventoConverter eventoConverter;
    private final UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Evento save(EventoModel model) throws WebException {

        validar(model);

        Evento entity = eventoConverter.modelToEntity(model);

        // Revisar si no se rompe
        if (findById(model.getId()) == null) {
            entity.setHabilitado(true);
            entity.setVisitantes(new ArrayList<>());
        }

        return eventoRepositorio.saveAndFlush(entity);
    }
    
    public void finalizar(String idEvento) {
    	Evento evento = findById(idEvento);
    	
    	evento.setHabilitado(false);
    	
    	eventoRepositorio.save(evento);
    	
    }
    
    public List<Evento> buscarHabilitados() {
    	
    	return eventoRepositorio.findAllHabilitados();
    }

    public void aceptarORechazar(String idEvento, String idUsuario, boolean acepted) {

        Evento evento = findById(idEvento);
        Usuario usuario = usuarioRepositorio.getOne(idUsuario);
        
//        Iterator<Usuario> it = evento.getPendientes().iterator();
//
//        while (it.hasNext()) {
//            Usuario pendiente = it.next();
//            if (pendiente.getId().equals(usuario.getId())) it.remove();
//        }

        int index = 0;
        Usuario pendiente;
        for (int i = 0; i < evento.getPendientes().size(); i++) {
            pendiente = evento.getPendientes().get(i);
            if (pendiente.getId().equals(idUsuario)) {
                index = i;
                break;
            }
        }

        evento.getPendientes().remove(index);
        
        if (acepted) {
        	
        	usuario.getEventos().add(evento);
        	evento.getVisitantes().add(usuario);
        	
        }

        eventoRepositorio.save(evento);
    }

    @Transactional
    public void eliminar(String id) throws WebException {

        eventoRepositorio.deleteById(id);

    }
    

    public Evento findById(String id) {
        if (id == null) {
            return null;
        }
        return eventoRepositorio.findById(id).orElse(null);
    }

//    public Page<Evento> findAll(Pageable paginable) {
//        return eventoRepositorio.findAll(paginable);
//    }
//
//    public Page<Evento> findAll(Pageable paginable, String q) {
//        return eventoRepositorio.findAll(paginable, "%" + q + "%");
//    }
//
//    public Page<Evento> findAllByFecha(Pageable paginable, String fechaGuiones) throws ParseException {
//        Date fecha = Fecha.parseFechaGuiones(fechaGuiones);
//        return eventoRepositorio.findAllByFecha(paginable, fecha);
//    }
    public List<Evento> findAll() {
        return eventoRepositorio.findAll();
    }

    private void validar(EventoModel model) throws WebException {

        if (model.getDescripcion().isEmpty() || model.getDescripcion() == null) {
            throw new WebException("La descripción no puede estar vacía.");
        }

        if (model.getNombre().isEmpty() || model.getNombre() == null) {
            throw new WebException("El nombre no puede estar vacío.");
        }

    }
}
