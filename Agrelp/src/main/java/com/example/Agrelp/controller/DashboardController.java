package com.example.Agrelp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Agrelp.repository.DefensivosRepository;
import com.example.Agrelp.repository.FerramentasRepository; // Certifique-se de que este repositório existe
import com.example.Agrelp.repository.MaquinasRepository;
import com.example.Agrelp.repository.MateriaisRepository;
import com.example.Agrelp.repository.SementesRepository;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private MaquinasRepository maquinasRepository; // Repositório para acessar as máquinas

    @Autowired
    private FerramentasRepository ferramentasRepository; // Repositório para acessar as ferramentas

    @Autowired
    private SementesRepository sementesRepository; // Repositório para acessar as sementes
    
    @Autowired
    private MateriaisRepository materiaisRepository; // Repositório para acessar as sementes
    
    @Autowired
    private DefensivosRepository defensivosRepository; // Repositório para acessar os defensivos
    
    @GetMapping
    public String mostrarDashboard(Model model) {
        long quantidadeMaquinas = maquinasRepository.count(); // Conta as máquinas no banco
        long quantidadeFerramentas = ferramentasRepository.count(); // Conta as ferramentas no banco
        long quantidadeSementes = sementesRepository.count(); // Conta as sementes no banco
        long quantidadeMateriais = materiaisRepository.count(); // Conta os materiais no banco
        long quantidadeDefensivos = defensivosRepository.count(); // Conta os defensivos no banco
        model.addAttribute("quantidadeMaquinas", quantidadeMaquinas); // Adiciona a quantidade de máquinas ao modelo
        model.addAttribute("quantidadeFerramentas", quantidadeFerramentas); // Adiciona a quantidade de ferramentas ao modelo
        model.addAttribute("quantidadeSementes", quantidadeSementes); // Adiciona a quantidade de sementes ao modelo
        model.addAttribute("quantidadeMateriais", quantidadeMateriais); // Adiciona a quantidade de materiais ao modelo
        model.addAttribute("quantidadeDefensivos", quantidadeDefensivos); // Adiciona a quantidade de defensivos ao modelo
        return "dashboard"; // Retorna o nome do arquivo HTML sem a extensão
    }
}
