package com.example.Agrelp.repository;

import com.example.Agrelp.model.Defensivos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefensivosRepository extends JpaRepository<Defensivos, Long> {
	 // Método para buscar ferramentas por nome
    List<Defensivos> findByNomeContainingIgnoreCase(String nome);
	
}
