package com.example.GestionVeterinaria.controller;

import com.example.GestionVeterinaria.entity.Veterinario;
import com.example.GestionVeterinaria.service.CitaService;
import com.example.GestionVeterinaria.service.ClienteService;
import com.example.GestionVeterinaria.service.VeterinarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final ClienteService clienteService;
    private  final VeterinarioService veterinarioService;
    private  final CitaService citaService;


    public AdminController(ClienteService clienteService, VeterinarioService veterinarioService, CitaService citaService) {
        this.clienteService = clienteService;
        this.veterinarioService = veterinarioService;
        this.citaService = citaService;
    }
    @GetMapping
    public String panelAdmin(Model model){
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("veterinarios", veterinarioService.listarTodos());
        model.addAttribute("citas", citaService.listarTodo());
        return "admin/panel";
    }

    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable Long id){
       clienteService.eliminar(id);
        return "redirect:/admin";
    }
    @GetMapping("/eliminarCita/{id}")
    public  String eliminarCita(@PathVariable Long id){
        citaService.eliminarCita(id);
        return "redirect:/admin";
    }
    @GetMapping("/eliminarVeterinario/{id}")
    public  String eliminarVeterinario(@PathVariable Long id){
    veterinarioService.eliminarVeterinario(id);
        return "redirect:/admin";
    }

}
