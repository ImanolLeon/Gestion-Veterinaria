package com.example.GestionVeterinaria.controller;

import com.example.GestionVeterinaria.entity.Cliente;
import com.example.GestionVeterinaria.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private  final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //listarClientes
    @GetMapping
    public String listar(Model model){
        model.addAttribute("clientes",clienteService.listarTodos());
        return "clientes/listar";
    }

    @GetMapping("/nuevo")
    public  String nuevo (Model model){
        model.addAttribute("cliente",new Cliente());
        return "clientes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente){
        clienteService.registrar(cliente);
        return "redirect:/clientes";
    }




}