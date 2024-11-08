package com.example.Agrelp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Agrelp.model.Ferramentas;

public interface FerramentasRepository extends JpaRepository<Ferramentas, Long> {
    // Aqui pode adicionar métodos customizados se necessário
}
