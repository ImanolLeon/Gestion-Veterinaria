package com.example.GestionVeterinaria.controller;

import com.example.GestionVeterinaria.entity.Veterinario;
import com.example.GestionVeterinaria.service.VeterinarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("veterinarios")
public class VeterinarioController
{

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public String listarVeterinarios(Model model){
        model.addAttribute("veterinarios",veterinarioService.listarTodos());
        model.addAttribute("veterinarioService",veterinarioService);
        model.addAttribute("contenido","veterinarios/listar");
        return "layout/base";
    }
    @GetMapping("/nuevo")
    public  String nuevoVeterinario(Model model){
        model.addAttribute("veterinario",new Veterinario());
        model.addAttribute("contenido", "veterinarios/formulario");
        return "layout/base";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Veterinario veterinario){
        veterinarioService.registraVeterinario(veterinario);
        return "redirect:/veterinarios";
    }


}
