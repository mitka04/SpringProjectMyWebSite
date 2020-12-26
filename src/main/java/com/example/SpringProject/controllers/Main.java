package com.example.SpringProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главня страница");
        return "home";
    }
}
