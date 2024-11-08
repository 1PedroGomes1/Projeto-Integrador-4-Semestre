package com.example.Agrelp.repository;

import com.example.Agrelp.model.Sementes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SementesRepository extends JpaRepository<Sementes, Long> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}
