package com.quintoimpacto.turismomendoza.app.controllers;

import com.quintoimpacto.turismomendoza.app.models.EventoModel;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.EVENTO_FORM_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.EVENTO_LABEL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/evento")
public class EventoController {
    
    @GetMapping("/form")
    public String form(ModelMap modelo) {
        modelo.addAttribute(EVENTO_LABEL, new EventoModel());
        return EVENTO_FORM_LABEL;
    }
    
}
