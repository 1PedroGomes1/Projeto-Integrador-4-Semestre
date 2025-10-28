package com.example.Agrelp.repository;

import com.example.Agrelp.model.Materiais;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaisRepository extends JpaRepository<Materiais, Long> {
	 // Método para buscar ferramentas por nome
    List<Materiais> findByNomeContainingIgnoreCase(String nome);}
