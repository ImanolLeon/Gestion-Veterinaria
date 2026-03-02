package com.example.GestionVeterinaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    @NotBlank(message = "Los nombres son obligatorios")
    @Column(length = 100, nullable = false)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(length = 100, nullable = false)
    private String apellidos;

    @Size(min = 9, max = 9, message = "El teléfono debe tener 9 dígitos")
    @Column(length = 9)
    private String telefono;

    @Email(message = "Correo electrónico inválido")
    @Column(length = 100)
    private String email;

    @Column(length = 200)
    private String direccion;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @PrePersist
    public void asignarFechaRegistro() {
        this.fechaRegistro = LocalDate.now();
    }

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Mascota> mascotas;
}








