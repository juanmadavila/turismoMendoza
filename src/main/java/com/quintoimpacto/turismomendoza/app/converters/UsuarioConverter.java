package com.quintoimpacto.turismomendoza.app.converters;

import com.quintoimpacto.turismomendoza.app.dao.UsuarioRepositorio;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.UsuarioModel;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioConverter extends Converter<UsuarioModel, Usuario>{

    private final UsuarioRepositorio usuarioRepositorio;
    
    @Override
    public Usuario modelToEntity(UsuarioModel model) throws WebException {
        
        Usuario entity = new Usuario();
        
        if (model.getId() != null) {
            entity = usuarioRepositorio.findById(model.getId()).get();
        }
        
        try {
            
            BeanUtils.copyProperties(entity, model);
            
        } catch (Exception e) {
            throw new WebException("No se pudo convertir de modelo a entidad: " + model.toString());
        }
        
        return entity;
    }

    @Override
    public UsuarioModel entityToModel(Usuario entity) throws WebException {

        UsuarioModel model = new UsuarioModel();
        
        try {
            
            BeanUtils.copyProperties(model, entity);
            
        } catch (Exception e) {
            throw new WebException("No se pudo convertir de entidad a model: " + entity.toString());
        }
        
        return model;
    }

    @Override
    public List<Usuario> modelsToEntities(List<UsuarioModel> models) throws WebException {
        List<Usuario> entities = new ArrayList<>();
        for (UsuarioModel model : models) {
            entities.add(modelToEntity(model));
        }
        
        return entities;
    }

    @Override
    public List<UsuarioModel> entitiesToModels(List<Usuario> entities) throws WebException {
        List<UsuarioModel> models = new ArrayList<>();
        for (Usuario entity : entities) {
            models.add(entityToModel(entity));
        }
        
        return models;
    }
    
}
