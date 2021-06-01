package com.quintoimpacto.turismomendoza.app.controllers;

import static com.quintoimpacto.turismomendoza.app.utils.Texts.MIS_EVENTOS_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.SESSION_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.USUARIO_LIST_LABEL;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/usuario")
@SessionAttributes("usuario")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @GetMapping("/list")
    public String userList(ModelMap model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return USUARIO_LIST_LABEL;
    }

    @GetMapping("/register")
    public String registro(ModelMap model) {
        return "register.html";
    }

    @GetMapping("/userForm")
    public String userForm(ModelMap model) {

        return "userForm.html";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes flash) {

        usuarioService.delete(id);
        flash.addFlashAttribute("success", "Usuario eliminado con éxito");

        return "redirect:/userList";
    }
    
    @GetMapping("/mis-eventos")
    public String misEventos(ModelMap modelo,HttpSession session) {
    	Usuario usuario = (Usuario) session.getAttribute(SESSION_LABEL);
    	modelo.put("usuario" , usuario);
    	modelo.put("eventos", usuario.getEventos());
    	
        return MIS_EVENTOS_LABEL;
    }

}
