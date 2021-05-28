package com.quintoimpacto.turismomendoza.app.converters;
import com.quintoimpacto.turismomendoza.app.dao.EventoRepositorio;
import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.EventoModel;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("EventoConverter")
@RequiredArgsConstructor(onConstructor_= @Autowired)
public class EventoConverter extends Converter<EventoModel, Evento>{
	private final EventoRepositorio eventoRepositorio;
	
    @Override
    public Evento modelToEntity(EventoModel model) throws WebException {
    	  
    	Evento entity = new Evento();
           if (model.getId() != null) {
               entity = eventoRepositorio.findById(model.getId()).get();
           }
           try {
               BeanUtils.copyProperties(entity, model);
           } catch (Exception e) {
               throw new WebException("No se pudo convertir de modelo a entidad: " + model.toString());
           }
           return entity;
    	
    }
    @Override
    public EventoModel entityToModel(Evento e) throws WebException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<Evento> modelsToEntities(List<EventoModel> m) throws WebException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<EventoModel> entitiesToModels(List<Evento> e) throws WebException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}