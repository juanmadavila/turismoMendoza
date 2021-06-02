package com.quintoimpacto.turismomendoza.app.controllers;

import com.quintoimpacto.turismomendoza.app.converters.UsuarioConverter;
import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.enums.Acceso;
import com.quintoimpacto.turismomendoza.app.enums.TipoDeEvento;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.EventoModel;
import com.quintoimpacto.turismomendoza.app.models.UsuarioModel;
import com.quintoimpacto.turismomendoza.app.service.EventoService;
import com.quintoimpacto.turismomendoza.app.service.UsuarioService;
import com.quintoimpacto.turismomendoza.app.utils.Fecha;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.EVENTO_FORM_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.EVENTO_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.EVENTO_PARTICIPANTES_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.REDIRECT_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.REDIRECT_PARTICIPANTES_LABEL;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/evento")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventoController {

	private final UsuarioConverter usuarioConverter;
	private final EventoService eventoService;
	private final UsuarioService usuarioService;

	@GetMapping("/form")
	public String form(HttpSession session, ModelMap modelo) {
		modelo.addAttribute(EVENTO_LABEL, new EventoModel());
		Usuario usuario = (Usuario) session.getAttribute("usuariosession");
		modelo.put("usuario", usuario);
		return EVENTO_FORM_LABEL;
	}

	@PostMapping("/save")
	public String save(HttpSession session, @Validated @ModelAttribute(EVENTO_LABEL) EventoModel eventoModel,
			BindingResult result, ModelMap modelo, @RequestParam String fecha, @RequestParam TipoDeEvento tipoDeEvento, @RequestParam Acceso acceso)
			throws ParseException {

		Usuario usuario = (Usuario) session.getAttribute("usuariosession");
		modelo.put("usuario", usuario);

		Date fechaDate = Fecha.parseFechaGuiones(fecha);
                String horario = eventoModel.getHorario().split(",")[2];

		try {

			UsuarioModel usuarioModel = usuarioConverter.entityToModel(usuario);
			eventoModel.setAnfitrion(usuarioModel);
			eventoModel.setFecha(fechaDate);
			eventoModel.setTipoDeEvento(tipoDeEvento);
			eventoModel.setAcceso(acceso);
                        eventoModel.setHorario(horario);
                        System.out.println(eventoModel.getHorario());
			Evento evento = eventoService.save(eventoModel);
			usuario.getEventos().add(evento);
			usuarioService.save(usuario);
			return REDIRECT_LABEL;

		} catch (WebException e) {
			loadModel(modelo, eventoModel, "update");
			modelo.addAttribute("error", e.getMessage());
		} catch (Exception e) {
			loadModel(modelo, eventoModel, "update");
			modelo.addAttribute("error", e.getMessage());
		}

		return EVENTO_FORM_LABEL;
	}

	@GetMapping("/eliminar")
	public String eliminarEvento(@RequestParam(required = true) String id) throws WebException {
		eventoService.eliminar(id);
		return REDIRECT_LABEL;
	}

	@GetMapping("/participar")
	public String participar(HttpSession session, @RequestParam String id) throws WebException {
		Usuario usuario = (Usuario) session.getAttribute("usuariosession");

		usuarioService.participar(id, usuario);

		return REDIRECT_LABEL;
	}
        
        @GetMapping("/participantes")
        public String participantes(ModelMap modelo, @RequestParam String idEvento) {
            Evento evento = eventoService.findById(idEvento);
            
            modelo.put("evento", evento);
            
            return EVENTO_PARTICIPANTES_LABEL;
        }
        
        @GetMapping("/check-invitations")
        public String checkInviations(@RequestParam String idEvento, @RequestParam String idUsuario, 
                @RequestParam Boolean acepted, RedirectAttributes modelo) {
            
            eventoService.aceptarORechazar(idEvento, idUsuario, acepted);
            
            String mensaje = acepted ? "Se añadió a lista de invitados" : "Se borró de la lista de pendientes";
            modelo.addFlashAttribute("mensaje", mensaje);
            
            return REDIRECT_PARTICIPANTES_LABEL+"idEvento?="+idEvento;
        }

	private void loadModel(ModelMap modelo, EventoModel eventoModel, String action) {
		modelo.addAttribute(EVENTO_LABEL, eventoModel);
		modelo.addAttribute("action", action);
	}

}
