package com.quintoimpacto.turismomendoza.app.controllers;

import com.quintoimpacto.turismomendoza.app.entity.Usuario;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.INDEX_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.LOGIN_LABEL;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return INDEX_LABEL;
    }

    @GetMapping("/login")
    public String login() {
        return LOGIN_LABEL;
    }

}
