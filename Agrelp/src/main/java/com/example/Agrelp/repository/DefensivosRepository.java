package com.example.Agrelp.repository;

import com.example.Agrelp.model.Defensivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefensivosRepository extends JpaRepository<Defensivos, Long> {
    // Aqui você pode adicionar consultas personalizadas, se necessário
}
