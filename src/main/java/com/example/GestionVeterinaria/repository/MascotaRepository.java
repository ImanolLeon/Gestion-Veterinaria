package com.example.GestionVeterinaria.repository;

import com.example.GestionVeterinaria.entity.Cliente;
import com.example.GestionVeterinaria.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Long> {

    // Buscar por cliente
    List<Mascota> findByCliente(Cliente cliente);

    // Buscar por ID del cliente
    List<Mascota> findByCliente_Id(Long id);

}
