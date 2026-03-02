package com.example.GestionVeterinaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String especialidad;

    @Column(length = 50, unique = true)
    private String telefono;

    @Column(nullable = false)
    private boolean activo = true;

    @OneToMany(mappedBy = "veterinario")
    private List<Cita> citas;
}
