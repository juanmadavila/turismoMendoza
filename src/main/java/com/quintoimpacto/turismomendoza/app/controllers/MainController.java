package com.quintoimpacto.turismomendoza.app.controllers;

import static com.quintoimpacto.turismomendoza.app.utils.Texts.INDEX_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.LOGIN_LABEL;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quintoimpacto.turismomendoza.app.converters.EventoConverter;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.EventoModel;
import com.quintoimpacto.turismomendoza.app.service.EventoService;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MainController {

    private final EventoService eventoService;
    private final EventoConverter eventoConverter;

    @GetMapping
    public String index(HttpSession session, ModelMap modelo) throws WebException {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);

        List<EventoModel> eventosModel = eventoConverter.entitiesToModels(eventoService.buscarHabilitados());
        modelo.put("eventos", eventosModel);

        JSONArray jsonArray = new JSONArray();
        for (EventoModel model : eventosModel) {
            JSONObject eventosJson = new JSONObject();
            eventosJson.appendField("nombre", model.getNombre());
            eventosJson.appendField("fecha", model.getFecha().toString());
            eventosJson.appendField("descripcion", model.getDescripcion());
            eventosJson.appendField("lat", model.getLat());
            eventosJson.appendField("lon", model.getLon());
            eventosJson.appendField("tipo", model.getTipoDeEvento().getAlias());
            eventosJson.appendField("urlPhotoAnfitrion", model.getAnfitrion().getPhotoUrl());
            eventosJson.appendField("nombreAnfitrion", model.getAnfitrion().getName());
            jsonArray.add(eventosJson);
        }

        modelo.put("eventosJson", jsonArray);

        return INDEX_LABEL;
    }

    @GetMapping("/login")
    public String login() {
        return LOGIN_LABEL;
    }

}
