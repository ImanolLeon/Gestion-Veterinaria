package com.example.GestionVeterinaria.controller;

import com.example.GestionVeterinaria.entity.Veterinario;
import com.example.GestionVeterinaria.service.VeterinarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("veterinarios",
                veterinarioService.listarTodos());
        return "admin/veterinarios/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("veterinario",
                new Veterinario());
        return "admin/veterinarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Veterinario veterinario){
        veterinarioService.registraVeterinario(veterinario);
        return "redirect:/admin/veterinarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("veterinario",
                veterinarioService.buscarId(id));
        return "admin/veterinarios/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        veterinarioService.eliminarVeterinario(id);
        return "redirect:/admin/veterinarios";
    }
}
