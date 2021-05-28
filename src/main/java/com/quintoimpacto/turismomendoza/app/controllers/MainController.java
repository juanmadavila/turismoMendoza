package com.quintoimpacto.turismomendoza.app.controllers;

import static com.quintoimpacto.turismomendoza.app.utils.Texts.INDEX_LABEL;
import static com.quintoimpacto.turismomendoza.app.utils.Texts.LOGIN_LABEL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index() {
        return INDEX_LABEL;
    }

    @GetMapping("/login")
    public String login() {
        return LOGIN_LABEL;
    }

}
