package com.example.GestionVeterinaria.repository;

import com.example.GestionVeterinaria.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {

    List<Cita> findByMascotaId(Long mascotaId);

    List<Cita> findByVeterinarioId(Long veterinarioId);

    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

    boolean existsByVeterinarioIdAndFechaHora(Long veterinarioId, LocalDateTime fechaHora);

}
