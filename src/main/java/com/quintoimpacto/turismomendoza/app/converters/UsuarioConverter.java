package com.quintoimpacto.turismomendoza.app.converters;

import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.UsuarioModel;
import java.util.List;

public class UsuarioConverter extends Converter<UsuarioModel, Usuario>{

    @Override
    public Usuario modelToEntity(UsuarioModel m) throws WebException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioModel entityToModel(Usuario e) throws WebException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> modelsToEntities(List<UsuarioModel> m) throws WebException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioModel> entitiesToModels(List<Usuario> e) throws WebException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
