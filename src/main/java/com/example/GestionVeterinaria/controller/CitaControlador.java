package com.example.GestionVeterinaria.controller;

import com.example.GestionVeterinaria.entity.Cita;
import com.example.GestionVeterinaria.repository.VeterinarioRepository;
import com.example.GestionVeterinaria.service.CitaService;
import com.example.GestionVeterinaria.service.MascotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/citas")
public class CitaControlador {

    private final CitaService citaService;
    private final MascotaService mascotaService;
    private  final VeterinarioRepository veterinarioRepository;

    public CitaControlador(CitaService citaService, MascotaService mascotaService, VeterinarioRepository veterinarioRepository) {
        this.citaService = citaService;
        this.mascotaService = mascotaService;
        this.veterinarioRepository = veterinarioRepository;
    }

    @GetMapping
    public String listarCitas(Model model){
        model.addAttribute("contenido","citas/listar");
        model.addAttribute("citas",citaService.listarPorFecha(LocalDate.now()));
        return "layout/base";
    }

    @GetMapping("/nueva")
    public String nuevaCita(Model model){
        model.addAttribute("contenido","citas/formulario");
        model.addAttribute("cita",new Cita());
        model.addAttribute("mascotas",mascotaService.listarTodas());
        return "layout/base";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cita cita,
                          @RequestParam Long id_mascota,
                          @RequestParam Long id_veterinario){

        citaService.registrarCita(cita,id_mascota,id_veterinario);
        return "redirect:/citas";

    }


}
