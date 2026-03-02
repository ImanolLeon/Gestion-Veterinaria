package com.example.GestionVeterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminPanel() {
        return "admin/dashboard";
    }

    @GetMapping("/user/dashboard")
    public String userPanel() {
        return "user/dashboard";
    }
}