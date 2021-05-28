package com.quintoimpacto.turismomendoza.app.converters;

import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.EventoModel;
import java.util.List;

public class EventoConverter extends Converter<EventoModel, Evento>{

    @Override
    public Evento modelToEntity(EventoModel m) throws WebException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
