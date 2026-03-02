package com.example.GestionVeterinaria.service;

import com.example.GestionVeterinaria.entity.*;
import com.example.GestionVeterinaria.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HistorialClinicoService {

    private final HistorialClinicoRepository historialRepository;
    private final CitaRepository citaRepository;

    public HistorialClinicoService(HistorialClinicoRepository historialRepository,
                                   CitaRepository citaRepository) {
        this.historialRepository = historialRepository;
        this.citaRepository = citaRepository;
    }

    public HistorialClinico crearHistorial(HistorialClinico historial, Long citaId) {

        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        // 🔥 Validación profesional
        if (cita.getEstado() != EstadoCita.FINALIZADA) {
            throw new RuntimeException("Solo se puede generar historial de citas finalizadas");
        }

        if (historialRepository.existsByCitaId(citaId)) {
            throw new RuntimeException("Esta cita ya tiene historial clínico");
        }

        historial.setFechaConsulta(LocalDate.now());
        historial.setMascota(cita.getMascota());
        historial.setCita(cita);

        return historialRepository.save(historial);
    }
}
