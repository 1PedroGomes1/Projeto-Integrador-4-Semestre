package com.example.Agrelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Agrelp.model.Ferramentas;
import java.util.List;

public interface FerramentasRepository extends JpaRepository<Ferramentas, Long> {
    // Método para buscar ferramentas por nome
    List<Ferramentas> findByNomeContainingIgnoreCase(String nome);
}
