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

    public List<Veterinario> listarTodos(){
        return veterinarioRepository.findAll();
    }

    public Veterinario buscarId(Long id_veterinario){
        return  veterinarioRepository.findById(id_veterinario).
                orElseThrow(()-> new RuntimeException("Veterinario no encontrado"));
    }

    public Veterinario registraVeterinario(Veterinario veterinario){
        return veterinarioRepository.save(veterinario);
    }

    public void eliminarVeterinario(Veterinario id_veterinario){
        veterinarioRepository.delete(id_veterinario);
    }
}
