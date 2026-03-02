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

    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }

    public List<Mascota> listarPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        return mascotaRepository.findByCliente_Id(clienteId);    }

    public Mascota buscarPorId(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    public Mascota guardar(Mascota mascota, Long clienteId) {

        if (mascota.getEdad() < 0) {
            throw new RuntimeException("La edad no puede ser negativa");
        }

        if (mascota.getNombre() == null || mascota.getNombre().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        mascota.setCliente(cliente);

        return mascotaRepository.save(mascota);
    }

    public void eliminar(Long id) {
        Mascota mascota = buscarPorId(id);
        mascotaRepository.delete(mascota);
    }
}