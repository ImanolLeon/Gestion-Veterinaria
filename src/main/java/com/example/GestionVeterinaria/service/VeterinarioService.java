package com.example.GestionVeterinaria.service;

import com.example.GestionVeterinaria.entity.Veterinario;
import com.example.GestionVeterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    // LISTAR SOLO ACTIVOS
    public List<Veterinario> listarTodos(){
        return veterinarioRepository.findByActivoTrue();
    }

    public Veterinario buscarId(Long id_veterinario){
        return veterinarioRepository.findById(id_veterinario)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
    }

    public Veterinario registraVeterinario(Veterinario veterinario){

        // Solo validar duplicado cuando es nuevo
        if(veterinario.getId() == null &&
                veterinarioRepository.existsByTelefono(veterinario.getTelefono())){
            throw new RuntimeException("Ya existe un veterinario con ese teléfono");
        }

        return veterinarioRepository.save(veterinario);
    }

    // ELIMINACIÓN LÓGICA
    public void eliminarVeterinario(Long id){
        Veterinario v = buscarId(id);
        v.setActivo(false);
        veterinarioRepository.save(v);
    }
}
