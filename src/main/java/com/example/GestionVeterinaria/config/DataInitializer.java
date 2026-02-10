package com.example.GestionVeterinaria.config;

import com.example.GestionVeterinaria.entity.Usuarios;
import com.example.GestionVeterinaria.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder)
    {

        return args ->{

            if(usuarioRepository.count()==0) {
                Usuarios admin = new Usuarios();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol("ADMIN");

                Usuarios usuario1= new Usuarios();
                usuario1.setUsername("usuario");
                usuario1.setPassword(passwordEncoder.encode("usuario"));
                usuario1.setRol("USER");

                usuarioRepository.save(admin);
                usuarioRepository.save(usuario1);
            }
        };
    }
}
