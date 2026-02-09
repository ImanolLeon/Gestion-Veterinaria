package com.example.GestionVeterinaria.controller;

import com.example.GestionVeterinaria.service.CitaService;
import com.example.GestionVeterinaria.service.MascotaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/citas")
public class CitaControlador {

    private final CitaService citaService;
    private final MascotaService mascotaService;

    public CitaControlador(CitaService citaService, MascotaService mascotaService) {
        this.citaService = citaService;
        this.mascotaService = mascotaService;
    }
}
