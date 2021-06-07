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
    private final EmailSender emailSender;

    @Transactional
    public Evento save(EventoModel model) throws WebException {

        validar(model);

        Evento entity = eventoConverter.modelToEntity(model);

//        if (findById(model.getId()) == null) {
//            entity.setVisitantes(new ArrayList<>());
//            entity.setPendientes(new ArrayList<>());
//        }
        entity.setHabilitado(true);

        return eventoRepositorio.saveAndFlush(entity);
    }

    @Transactional(rollbackFor = {Exception.class, WebException.class})
    public void borrarInvitacion(String idEvento, String idUsuario) {
        Evento evento = findById(idEvento);
        Usuario usuario = usuarioRepositorio.findById(idUsuario).get();

        evento.getVisitantes().remove(usuario);
        usuario.getEventos().remove(evento);

        eventoRepositorio.save(evento);
    }

    @Transactional(rollbackFor = {Exception.class, WebException.class})
    public void finalizar(String idEvento) throws WebException, Exception {
        Evento evento = findById(idEvento);
        evento.setHabilitado(false);
        eventoRepositorio.save(evento);

//    	emailSender.sendEventoDeshabilitado(evento);
    }

    @Transactional(rollbackFor = {Exception.class, WebException.class})
    public void aceptarORechazar(String idEvento, String idUsuario, boolean acepted) throws WebException, Exception {

        Evento evento = findById(idEvento);
        Usuario usuario = usuarioRepositorio.getOne(idUsuario);

        evento.getPendientes().remove(usuario);

        if (acepted) {
            usuario.getEventos().add(evento);
            evento.getVisitantes().add(usuario);
        }

        eventoRepositorio.save(evento);
//        emailSender.sendAcceptedUser(usuario, evento, acepted);
    }

    @Transactional(rollbackFor = {Exception.class, WebException.class})
    public void eliminar(String id) throws WebException {

        eventoRepositorio.deleteById(id);

    }

    public List<Evento> buscarHabilitados() {

        return eventoRepositorio.findAllHabilitados();
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

    public List<Evento> findBy(String q) {
        return eventoRepositorio.findBy("%" + q + "%");
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
