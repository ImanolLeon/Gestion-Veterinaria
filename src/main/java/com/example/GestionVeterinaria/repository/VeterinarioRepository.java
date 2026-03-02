package com.example.GestionVeterinaria.repository;

import com.example.GestionVeterinaria.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario,Long> {

    List<Veterinario> findByActivoTrue();

    boolean existsByTelefono(String telefono);
}
