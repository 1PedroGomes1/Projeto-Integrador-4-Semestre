package com.example.Agrelp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Agrelp.model.Sementes;
import com.example.Agrelp.repository.SementesRepository;

@Controller
@RequestMapping("/sementes")
public class SementesController {

    @Autowired
    private SementesRepository sementesRepository;

    @GetMapping("/adicionar")
    public String mostrarFormulario() {
        return "formSementes"; // Retorna o nome do arquivo HTML sem a extensão
    }

    @PostMapping
    public String adicionarSemente(@ModelAttribute Sementes sementes) {
        sementesRepository.save(sementes); // Salva a semente no banco de dados
        return "redirect:/sementes"; // Redireciona após sucesso
    }

    @GetMapping("/cards")
    public String listarSementes(Model model) {
        model.addAttribute("sementes", sementesRepository.findAll());
        return "listarSementes"; // Retorna o nome do arquivo HTML sem a extensão
    }

    @GetMapping
    public String listarSementesTabelas(Model model) {
        model.addAttribute("sementes", sementesRepository.findAll());
        return "listarSementesTabelas"; // Retorna o nome do arquivo HTML sem a extensão
    }
    
    // Método para mostrar o formulário de edição
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Sementes semente = sementesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Semente não encontrada com ID: " + id));
        model.addAttribute("semente", semente);
        return "editarSementes"; // Retorna o nome do arquivo HTML sem a extensão
    }

    // Método para processar a edição da semente
    @PostMapping("/editar/{id}")
    public String editarSemente(@PathVariable Long id, @ModelAttribute Sementes sementes) {
        sementes.setId(id); // Define o ID da semente a ser editada
        sementesRepository.save(sementes); // Salva as alterações no banco de dados
        return "redirect:/sementes"; // Redireciona após sucesso
    }

    // Método para deletar uma semente
    @GetMapping("/deletar/{id}")
    public String deletarSemente(@PathVariable Long id) {
        sementesRepository.deleteById(id);
        return "redirect:/sementes"; // Redireciona para a lista após deletar
    }
    
 // Método para buscar sementes por nome
    @GetMapping("/buscar")
    public String buscarSementes(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false, defaultValue = "cards") String formato,
            Model model) {
        
        List<Sementes> sementes;

        if (nome == null || nome.trim().isEmpty()) {
            // Retorna todas as sementes se o campo estiver vazio
            sementes = sementesRepository.findAll();
        } else {
            // Realiza a busca pelo nome
            sementes = sementesRepository.findByNomeContainingIgnoreCase(nome);
        }

        model.addAttribute("sementes", sementes);

        // Retorna o template correto com base no formato solicitado
        if ("tabelas".equalsIgnoreCase(formato)) {
            return "listarSementesTabelas";
        } else {
            return "listarSementes";
        }
    }

    
}
