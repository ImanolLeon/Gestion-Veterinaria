package com.example.GestionVeterinaria.repository;

import com.example.GestionVeterinaria.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {

    List<Cita> findByMascotaId(Long mascota_id);
    List<Cita> findByVeterinario(Long veterinario_id);
    List<Cita> findByFechaCita(LocalDate fechaCita);
    int countByVeterinarioId(Long veterinarioId);
    void deleteById(Long id);
}
