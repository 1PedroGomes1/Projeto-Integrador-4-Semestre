package com.example.Agrelp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Maquinas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaquinas;

    private String nome;
    private String status;
    private String modelo;
    private int anoFabricacao;
    private String imagemUrl;
    
    private String tipoMaquina;
    private double capacidadeCombustivel;
    private int horasTrabalhadas;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataUltimaManutencao;
    private String descricao;
    private String numeroSerie;

    public Maquinas(Long idMaquinas, String nome, String status, String modelo, int anoFabricacao, String imagemUrl,
                    String tipoMaquina, double capacidadeCombustivel, int horasTrabalhadas, LocalDate dataUltimaManutencao,
                    String descricao, String numeroSerie) {
        super();
        this.idMaquinas = idMaquinas;
        this.nome = nome;
        this.status = status;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.imagemUrl = imagemUrl;
        this.tipoMaquina = tipoMaquina;
        this.capacidadeCombustivel = capacidadeCombustivel;
        this.horasTrabalhadas = horasTrabalhadas;
        this.dataUltimaManutencao = dataUltimaManutencao;
        this.descricao = descricao;
        this.numeroSerie = numeroSerie;
    }

    public Maquinas() {
        super();
    }

    // Getters e Setters

    public Long getIdMaquinas() {
        return idMaquinas;
    }

    public void setIdMaquinas(Long idMaquinas) {
        this.idMaquinas = idMaquinas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getTipoMaquina() {
        return tipoMaquina;
    }

    public void setTipoMaquina(String tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }

    public double getCapacidadeCombustivel() {
        return capacidadeCombustivel;
    }

    public void setCapacidadeCombustivel(double capacidadeCombustivel) {
        this.capacidadeCombustivel = capacidadeCombustivel;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public LocalDate getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }

    public void setDataUltimaManutencao(LocalDate dataUltimaManutencao) {
        this.dataUltimaManutencao = dataUltimaManutencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
}
