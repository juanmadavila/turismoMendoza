package com.quintoimpacto.turismomendoza.app.controllers;

import com.quintoimpacto.turismomendoza.app.converters.UsuarioConverter;
import com.quintoimpacto.turismomendoza.app.entity.Evento;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.enums.TipoDeEvento;
import com.quintoimpacto.turismomendoza.app.error.WebException;
import com.quintoimpacto.turismomendoza.app.models.EventoModel;
import com.quintoimpacto.turismomendoza.app.models.UsuarioModel;
import com.quintoimpacto.turismomendoza.app.service.EventoService;
import com.quintoimpacto.turismomendoza.app.service.UsuarioService;
import com.quintoimpacto.turismomendoza.app.utils.Fecha;
import com.quintoimpacto.turismomendoza.app.utils.Texts;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.EVENTO_FORM_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.EVENTO_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.INDEX_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.REDIRECT_LABEL;
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
            BindingResult result, ModelMap modelo,
            @RequestParam String fecha, 
            @RequestParam TipoDeEvento tipoDeEvento) throws ParseException {
        
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        
        Date fechaDate = Fecha.parseFechaGuiones(fecha);
        System.out.println(fechaDate);
        
        try {
            
        
                UsuarioModel usuarioModel = usuarioConverter.entityToModel(usuario);
                eventoModel.setAnfitrion(usuarioModel);
                eventoModel.setFecha(fechaDate);
                eventoModel.setTipoDeEvento(tipoDeEvento);
                //entity.getAnfitrion().getEventos().add(entity);
                Evento evento = eventoService.save(eventoModel);
                return REDIRECT_LABEL;
            
        } catch (WebException e) {
            loadModel(modelo, eventoModel, "update");
            modelo.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            loadModel(modelo, eventoModel, "update");
            modelo.addAttribute("error", e.getMessage());
        }
        

        return Texts.EVENTO_FORM_LABEL;
    }
    
    @GetMapping("/eliminar")
    private String eliminarEvento(@RequestParam(required = true) String id) throws WebException {
        eventoService.eliminar(id);
        return REDIRECT_LABEL;
    }
    
    @GetMapping("/participar")
    private String participar(HttpSession session , @RequestParam String id) throws WebException {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        
        usuarioService.participar(id, usuario);
        
        return REDIRECT_LABEL;
    }

    private void loadModel(ModelMap modelo, EventoModel eventoModel, String action) {
        modelo.addAttribute(EVENTO_LABEL, eventoModel);
        modelo.addAttribute("action", action);
    }

}
