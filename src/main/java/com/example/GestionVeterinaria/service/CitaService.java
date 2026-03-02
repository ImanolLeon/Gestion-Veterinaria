package com.example.GestionVeterinaria.service;

import com.example.GestionVeterinaria.entity.*;
import com.example.GestionVeterinaria.repository.CitaRepository;
import com.example.GestionVeterinaria.repository.MascotaRepository;
import com.example.GestionVeterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaService(CitaRepository citaRepository,
                       MascotaRepository mascotaRepository,
                       VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    public Cita registrarCita(Cita cita, Long idMascota, Long idVeterinario){

        if(cita.getFechaHora().isBefore(LocalDateTime.now())){
            throw new RuntimeException("No se puede registrar una cita en el pasado");
        }

        Mascota mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Veterinario veterinario = veterinarioRepository.findById(idVeterinario)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        if(!veterinario.isActivo()){
            throw new RuntimeException("El veterinario está inactivo");
        }

        boolean ocupado = citaRepository
                .existsByVeterinarioIdAndFechaHora(idVeterinario, cita.getFechaHora());

        if(ocupado){
            throw new RuntimeException("El veterinario ya tiene una cita en ese horario");
        }

        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        cita.setEstado(EstadoCita.PROGRAMADA);

        return citaRepository.save(cita);
    }

    // 👇 NUEVO MÉTODO QUE FALTABA
    public List<Cita> listarPorFecha(LocalDate fecha){

        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(23,59,59);

        return citaRepository.findByFechaHoraBetween(inicio, fin);
    }

    public List<Cita> listarTodas(){
        return citaRepository.findAll();
    }

    public List<Cita> listarPorMascota(Long idMascota){
        return citaRepository.findByMascotaId(idMascota);
    }

    public List<Cita> listarPorVeterinario(Long idVeterinario){
        return citaRepository.findByVeterinarioId(idVeterinario);
    }

    public void cambiarEstado(Long citaId, EstadoCita nuevoEstado){
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setEstado(nuevoEstado);
        citaRepository.save(cita);
    }
}