package com.example.Agrelp.repository;

import com.example.Agrelp.model.Sementes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SementesRepository extends JpaRepository<Sementes, Long> {
	 // Método para buscar ferramentas por nome
    List<Sementes> findByNomeContainingIgnoreCase(String nome);}
