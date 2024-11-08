package com.example.Agrelp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sementes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataColheita;
    private LocalDate dataValidade;
    private int quantidade;
    private String condicoesArmazenamento;
    private String produtorFabricante;



	public Sementes(Long id, String nome, LocalDate dataColheita, LocalDate dataValidade, int quantidade,
			String condicoesArmazenamento, String produtorFabricante) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataColheita = dataColheita;
		this.dataValidade = dataValidade;
		this.quantidade = quantidade;
		this.condicoesArmazenamento = condicoesArmazenamento;
		this.produtorFabricante = produtorFabricante;
	}

	public Sementes() {
		super();
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDate getDataColheita() {
		return dataColheita;
	}


	public void setDataColheita(LocalDate dataColheita) {
		this.dataColheita = dataColheita;
	}


	public LocalDate getDataValidade() {
		return dataValidade;
	}


	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public String getCondicoesArmazenamento() {
		return condicoesArmazenamento;
	}


	public void setCondicoesArmazenamento(String condicoesArmazenamento) {
		this.condicoesArmazenamento = condicoesArmazenamento;
	}


	public String getProdutorFabricante() {
		return produtorFabricante;
	}


	public void setProdutorFabricante(String produtorFabricante) {
		this.produtorFabricante = produtorFabricante;
	}

	
    // Getters e Setters
    
}
