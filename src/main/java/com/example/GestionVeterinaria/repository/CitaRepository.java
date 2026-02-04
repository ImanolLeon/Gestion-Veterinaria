package com.example.GestionVeterinaria.repository;

import com.example.GestionVeterinaria.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {

    List<Cita> findByMascotaId(Long mascota_id);
    List<Cita> findByVeterinario(Long veterinario_id);
    List<Cita> findByFecha(java.time.LocalDate fecha);
}
