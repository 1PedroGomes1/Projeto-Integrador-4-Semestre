package com.example.Agrelp.controller;

import com.example.Agrelp.model.Ferramentas;
import com.example.Agrelp.repository.FerramentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ferramentas")
public class FerramentasController {

    @Autowired
    private FerramentasRepository ferramentasRepository;

    @GetMapping("/adicionar")
    public String mostrarFormulario() {
        return "formferramentas";
    }

    @GetMapping
    public String listarFerramentas(Model model) {
        List<Ferramentas> ferramentas = ferramentasRepository.findAll();
        model.addAttribute("ferramentas", ferramentas);
        return "listarFerramentasTabelas";
    }

    @GetMapping("/cards")
    public String listarFerramentasTabelas(Model model) {
        List<Ferramentas> ferramentas = ferramentasRepository.findAll();
        model.addAttribute("ferramentas", ferramentas);
        return "listarFerramentas";
    }
    
    @PostMapping
    public String adicionarFerramenta(@ModelAttribute Ferramentas ferramentas) {
        ferramentasRepository.save(ferramentas);
        return "redirect:/ferramentas";
    }

    // Método para exibir o formulário de edição
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Optional<Ferramentas> ferramentaOptional = ferramentasRepository.findById(id);
        if (ferramentaOptional.isPresent()) {
            model.addAttribute("ferramenta", ferramentaOptional.get());
            return "editarferramentas"; // Reutiliza o formulário para edição
        } else {
            return "redirect:/ferramentas"; // Redireciona se o ID não for encontrado
        }
    }

    // Método para salvar as alterações da edição
    @PostMapping("/editar/{id}")
    public String atualizarFerramenta(@PathVariable Long id, @ModelAttribute Ferramentas ferramentaAtualizada) {
        Optional<Ferramentas> ferramentaOptional = ferramentasRepository.findById(id);
        if (ferramentaOptional.isPresent()) {
            Ferramentas ferramentaExistente = ferramentaOptional.get();
            // Atualiza os dados
            ferramentaExistente.setNome(ferramentaAtualizada.getNome());
            ferramentaExistente.setMarca(ferramentaAtualizada.getMarca());
            ferramentaExistente.setDescricao(ferramentaAtualizada.getDescricao());
            ferramentaExistente.setQuantidade(ferramentaAtualizada.getQuantidade());
            ferramentasRepository.save(ferramentaExistente);
        }
        return "redirect:/ferramentas"; // Redireciona para a lista de ferramentas
    }

    // Método para deletar uma ferramenta
    @GetMapping("/deletar/{id}")
    public String deletarFerramenta(@PathVariable Long id) {
        ferramentasRepository.deleteById(id);
        return "redirect:/ferramentas"; // Redireciona para a lista após deletar
    }

    // Método para buscar ferramentas por nome
    @GetMapping("/buscar")
    public String buscarFerramentas(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false, defaultValue = "cards") String formato,
            Model model) {
        
        List<Ferramentas> ferramentas;

        if (nome == null || nome.trim().isEmpty()) {
            // Retorna todas as ferramentas se o campo estiver vazio
            ferramentas = ferramentasRepository.findAll();
        } else {
            // Realiza a busca pelo nome
            ferramentas = ferramentasRepository.findByNomeContainingIgnoreCase(nome);
        }

        model.addAttribute("ferramentas", ferramentas);

        // Retorna o template correto com base no formato solicitado
        if ("tabelas".equalsIgnoreCase(formato)) {
            return "listarFerramentasTabelas";
        } else {
            return "listarFerramentas";
        }
    }
    
}
