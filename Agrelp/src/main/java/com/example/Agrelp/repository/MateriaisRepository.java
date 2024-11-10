package com.example.Agrelp.repository;

import com.example.Agrelp.model.Materiais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaisRepository extends JpaRepository<Materiais, Long> {
    // Aqui podem ser adicionados métodos personalizados de consulta, se necessário
}
