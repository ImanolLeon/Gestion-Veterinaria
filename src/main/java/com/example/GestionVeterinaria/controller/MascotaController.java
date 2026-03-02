package com.example.GestionVeterinaria.controller;

import com.example.GestionVeterinaria.entity.Mascota;
import com.example.GestionVeterinaria.entity.Cliente;
import com.example.GestionVeterinaria.service.ClienteService;
import com.example.GestionVeterinaria.service.MascotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/clientes/{clienteId}/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;
    private final ClienteService clienteService;

    public MascotaController(MascotaService mascotaService,
                             ClienteService clienteService) {
        this.mascotaService = mascotaService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(@PathVariable Long clienteId, Model model) {

        Cliente cliente = clienteService.buscarPorId(clienteId);
        model.addAttribute("cliente", cliente);
        model.addAttribute("mascotas",
                mascotaService.listarPorCliente(clienteId));

        return "mascotas/listar";
    }

    @GetMapping("/nueva")
    public String nueva(@PathVariable Long clienteId, Model model) {

        model.addAttribute("mascota", new Mascota());
        model.addAttribute("clienteId", clienteId);

        return "mascotas/formulario";
    }

    @GetMapping("/{mascotaId}/editar")
    public String editar(@PathVariable Long clienteId,
                         @PathVariable Long mascotaId,
                         Model model) {

        Mascota mascota = mascotaService.buscarPorId(mascotaId);

        model.addAttribute("mascota", mascota);
        model.addAttribute("clienteId", clienteId);

        return "mascotas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@PathVariable Long clienteId,
                          @ModelAttribute Mascota mascota) {

        mascotaService.guardar(mascota, clienteId);

        return "redirect:/admin/clientes/" + clienteId + "/mascotas";
    }

    @PostMapping("/{mascotaId}/eliminar")
    public String eliminar(@PathVariable Long clienteId,
                           @PathVariable Long mascotaId) {

        mascotaService.eliminar(mascotaId);

        return "redirect:/admin/clientes/" + clienteId + "/mascotas";
    }
}