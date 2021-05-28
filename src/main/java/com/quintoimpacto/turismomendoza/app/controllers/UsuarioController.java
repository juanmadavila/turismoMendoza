package com.quintoimpacto.turismomendoza.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import com.quintoimpacto.turismomendoza.app.service.UsuarioService;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.USUARIO_LIST_LABEL;
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

    @PostMapping("/userForm")
    public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
            SessionStatus status) {

        if (usuario.getId() != null) {
            flash.addFlashAttribute("success", "Usuario editado con éxito");

        } else {
            flash.addFlashAttribute("success", "Usuario creado con éxito");
        }
        usuarioService.save(usuario);
        status.setComplete();
        return "redirect:userList";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes flash) {

        usuarioService.delete(id);
        flash.addFlashAttribute("success", "Usuario eliminado con éxito");

        return "redirect:/userList";
    }

}
