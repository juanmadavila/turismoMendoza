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
import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.EventoModel;
import com.quintoimpacto.turismomendoza.app.service.EventoService;

import lombok.RequiredArgsConstructor;

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
        
		List<Evento> eventos = eventoService.findAll();  
		List<EventoModel> eventosModel = eventoConverter.entitiesToModels(eventos);
		modelo.put("eventos", eventosModel);
		return INDEX_LABEL;

	}

	@GetMapping("/login")
	public String login() {
		return LOGIN_LABEL;
	}

}
