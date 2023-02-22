package com.bolsadeideas.springboot.datajpa.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal, RedirectAttributes flash) {
    if (principal != null) {
        flash.addFlashAttribute("info", "Ya ha iniciado sesion anteriormente");
        return "redirect:/listar";
    }

    if (error != null) {
        model.addAttribute("error", "Error en el login: Nombre de usuario o contrasena incorrecta, por favor vuelve ingresarla");
    }
        return "login/login";
    }



}
