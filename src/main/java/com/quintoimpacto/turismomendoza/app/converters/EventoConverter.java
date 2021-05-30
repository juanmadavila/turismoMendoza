package com.quintoimpacto.turismomendoza.app.converters;

import com.quintoimpacto.turismomendoza.app.dao.EventoRepositorio;
import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.EventoModel;
import com.quintoimpacto.turismomendoza.app.models.UsuarioModel;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("EventoConverter")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventoConverter extends Converter<EventoModel, Evento> {

	private final EventoRepositorio eventoRepositorio;
	private final UsuarioConverter usuarioConverter;

	@Override
	public Evento modelToEntity(EventoModel model) throws WebException {

		Evento entity = new Evento();

		if (model.getId() != null) {
			entity = eventoRepositorio.findById(model.getId()).get();
		}
		if (model.getVisitantes() == null) {
			model.setVisitantes(new ArrayList<>());
		}
		try {
			Usuario anfitrion = usuarioConverter.modelToEntity(model.getAnfitrion());
			entity.setAnfitrion(anfitrion);
			entity.setDescripcion(model.getDescripcion());
			entity.setFecha(model.getFecha());
			entity.setHabilitado(model.getHabilitado());
			entity.setLat(model.getLat());
			entity.setLon(model.getLon());
			entity.setNombre(model.getNombre());
			entity.setTipoDeEvento(model.getTipoDeEvento());
			if (entity.getVisitantes() == null)
				entity.setVisitantes(new ArrayList<>());
//            BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			throw new WebException(
					"No se pudo convertir de modelo a entidad: " + model.toString() + " " + entity.toString());
		}
		return entity;

	}

	@Override
	public EventoModel entityToModel(Evento entity) throws WebException {
		EventoModel model = new EventoModel();

		try {

			BeanUtils.copyProperties(model, entity);

		} catch (Exception e) {
			throw new WebException("No se pudo convertir de entidad a model: " + entity.toString());
		}

		return model;

	}

	@Override
	public List<Evento> modelsToEntities(List<EventoModel> models) throws WebException {
		List<Evento> eventos = new ArrayList<>();
		for (EventoModel model : models) {

			eventos.add(modelToEntity(model));
		}
		return eventos;
	}

	@Override
	public List<EventoModel> entitiesToModels(List<Evento> entities) throws WebException {
		List<EventoModel> models = new ArrayList<>();
				for(Evento entity: entities) {
					
					models.add(entityToModel(entity));
				}
				return models;
	}
}
