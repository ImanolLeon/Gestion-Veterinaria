package com.example.GestionVeterinaria.controller;

import com.example.GestionVeterinaria.entity.Cliente;
import com.example.GestionVeterinaria.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Listar
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("contenido", "admin/clientes/list");
        return "layout/base";
    }

    // Formulario nuevo
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("contenido", "admin/clientes/form");
        return "layout/base";
    }

    // Guardar
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Cliente cliente,
                          BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            model.addAttribute("contenido", "admin/clientes/form");
            return "layout/base";
        }

        try {
            if (cliente.getId() == null) {
                clienteService.registrar(cliente);
            } else {
                clienteService.actualizar(cliente);
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("contenido", "admin/clientes/form");
            return "layout/base";
        }

        return "redirect:/admin/clientes";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteService.buscarPorId(id));
        model.addAttribute("contenido", "admin/clientes/form");
        return "layout/base";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "redirect:/admin/clientes";
    }

    // Buscar
    @GetMapping("/buscar")
    public String buscar(@RequestParam String texto, Model model) {
        model.addAttribute("clientes",
                clienteService.buscarPorNombre(texto));
        model.addAttribute("contenido", "admin/clientes/list");
        return "layout/base";
    }
}