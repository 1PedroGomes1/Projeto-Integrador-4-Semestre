package com.example.Agrelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Agrelp.model.Maquinas;

public interface MaquinasRepository extends JpaRepository<Maquinas, Long>{

	 // Método para buscar ferramentas por nome
    List<Maquinas> findByNomeContainingIgnoreCase(String nome);
}
