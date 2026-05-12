package br.gov.sp.fateczl.museu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("msg", "Login!! (eu acho)");
        return "index";
    }
}
