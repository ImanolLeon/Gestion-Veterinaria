package com.example.GestionVeterinaria.service;

import com.example.GestionVeterinaria.entity.Cliente;
import com.example.GestionVeterinaria.entity.Mascota;
import com.example.GestionVeterinaria.repository.ClienteRepository;
import com.example.GestionVeterinaria.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final ClienteRepository clienteRepository;

    public MascotaService(MascotaRepository mascotaRepository,
                          ClienteRepository clienteRepository) {
        this.mascotaRepository = mascotaRepository;
        this.clienteRepository = clienteRepository;
    }

    // Listar todas las mascotas
    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }

    // Listar mascotas por cliente
    public List<Mascota> listarPorCliente(Long clienteId) {
        return mascotaRepository.findByCliente_id(clienteId);
    }

    // Guardar mascota asociada a un cliente
    public Mascota guardar(Mascota mascota, Long clienteId) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        mascota.setCliente(cliente);

        return mascotaRepository.save(mascota);
    }

    // Eliminar mascota
    public void eliminar(Long mascotaId) {
        mascotaRepository.deleteById(mascotaId);
    }
}
