package com.example.GestionVeterinaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Fecha_cita")
    private LocalDate fechaCita;

    @Column(name = "hora_cita")
    private LocalDateTime hora_cita;

    @Column (length = 100)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "mascota_id",nullable = false)
    private Mascota mascota;


    @Column(length = 100)
    private String estado;
    @ManyToOne
    @JoinColumn(name = "veterinario_id",nullable = false)
    private Veterinario veterinario;


}
