package com.example.GestionVeterinaria.service;

import com.example.GestionVeterinaria.entity.Cita;
import com.example.GestionVeterinaria.entity.Mascota;
import com.example.GestionVeterinaria.entity.Veterinario;
import com.example.GestionVeterinaria.repository.CitaRepository;
import com.example.GestionVeterinaria.repository.MascotaRepository;
import com.example.GestionVeterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
//En esta clase se implementan los metodos para registrar citas, listar citas por mascota, veterinario y fecha, y cambiar el estado de una cita
@Service
public class CitaService {

    //llamamos metodos
    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;


    //constructores
    public CitaService(CitaRepository citaRepository, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }


    public Cita registrarCita(Cita cita,Long id_Mascota,Long id_Veterinario){
        //Validar datos
        Mascota mascota = mascotaRepository.findById(id_Mascota).
                orElseThrow(()->new RuntimeException("Mascota no encontrada"));

        Veterinario veterinario = veterinarioRepository.findById(id_Veterinario).
                orElseThrow(()->new RuntimeException("Veterinario no encontrado"));

        //Asociamos relaciones
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);

        cita.setEstado("Programada");

        //Para guardar es save
        return citaRepository.save(cita);

    }

    public List<Cita> listarTodo(){
        return citaRepository.findAll();
    }
    public List<Cita> listarPorMascota(Long id_mascota){
        return citaRepository.findByMascotaId(id_mascota);
    }

    public List<Cita> listarPorVeterinario (Long id_veterinario){
        return  citaRepository.findByVeterinario(id_veterinario);
    }

    public List<Cita> listarPorFecha (LocalDate fecha_cita){
        return citaRepository.findByFechaCita(fecha_cita);
    }

    public void cambiarEstadoCita (Long cita_id , String estado){
        Cita cita1 = citaRepository.findById(cita_id).
                orElseThrow(()->new RuntimeException("No se encontró cita"));

        cita1.setEstado(estado);
        //Guardamos
        citaRepository.save(cita1);
    }



}
