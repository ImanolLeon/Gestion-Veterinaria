package com.example.GestionVeterinaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Historial Clinico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorialClinico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_Consulta")
    private LocalDate fecha_consulta;

    @Column (length = 200)
    private String diganostico;


    @Column (length = 200)
    private String tratamiento;


    @Column (length = 200)
    private String observaciones;

    //Clave de 1 a uno


}
