package com.example.GestionVeterinaria.controller;

import com.example.GestionVeterinaria.entity.HistorialClinico;
import com.example.GestionVeterinaria.service.HistorialClinicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/historial")
public class HistorialClinicoController {

    private final HistorialClinicoService historialService;

    public HistorialClinicoController(HistorialClinicoService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/nuevo/{citaId}")
    public String nuevoHistorial(@PathVariable Long citaId, Model model) {

        model.addAttribute("historial", new HistorialClinico());
        model.addAttribute("citaId", citaId);

        return "historial/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute HistorialClinico historial,
                          @RequestParam Long citaId) {

        historialService.crearHistorial(historial, citaId);

        return "redirect:/admin/citas";
    }
}
