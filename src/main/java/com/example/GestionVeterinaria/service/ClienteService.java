package com.example.GestionVeterinaria.service;

import com.example.GestionVeterinaria.entity.Cliente;
import com.example.GestionVeterinaria.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Buscar por ID
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    // Buscar por nombre
    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombresContainingIgnoreCase(nombre);
    }

    // Registrar
    public Cliente registrar(Cliente cliente) {

        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }

        return clienteRepository.save(cliente);
    }

    // Actualizar
    public Cliente actualizar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Eliminar
    public void eliminar(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }
}
