package com.example.GestionVeterinaria.repository;

import com.example.GestionVeterinaria.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByDni(String dni);

    boolean existsByDni(String dni);

    List<Cliente> findByNombresContainingIgnoreCase(String nombres);
}