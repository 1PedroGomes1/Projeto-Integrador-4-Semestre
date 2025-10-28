package com.example.Agrelp.controller;

import com.example.Agrelp.model.Defensivos;
import com.example.Agrelp.repository.DefensivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/defensivos")
public class DefensivosController {

    @Autowired
    private DefensivosRepository defensivosRepository;

    @GetMapping("/adicionar")
    public String mostrarFormulario() {
        return "formDefensivos"; // Formulário para adicionar um novo defensivo
    }

 // Listar todos os materiais
    @GetMapping("/cards")
    public String listarDefensivos(Model model) {
        List<Defensivos> defensivos = defensivosRepository.findAll();
        model.addAttribute("defensivos", defensivos);
        return "listarDefensivos"; // Exibe a lista de defensivos
    }
    
    @GetMapping
    public String listarDefensivosTabelas(Model model) {
        List<Defensivos> defensivos = defensivosRepository.findAll();
        model.addAttribute("defensivos", defensivos);
        return "listarDefensivosTabelas"; // Exibe a lista de defensivos
    }

    @PostMapping
    public String adicionarDefensivo(@ModelAttribute Defensivos defensivo) {
        defensivosRepository.save(defensivo); // Salva o novo defensivo no banco
        return "redirect:/defensivos"; // Redireciona para a lista de defensivos
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Optional<Defensivos> defensivoOptional = defensivosRepository.findById(id);
        if (defensivoOptional.isPresent()) {
            model.addAttribute("defensivo", defensivoOptional.get()); // Passa o defensivo para edição
            return "editarDefensivos"; // Exibe o formulário de edição
        } else {
            return "redirect:/defensivos"; // Redireciona se o ID não for encontrado
        }
    }

    @PostMapping("/editar/{id}")
    public String atualizarDefensivo(@PathVariable Long id, @ModelAttribute Defensivos defensivoAtualizado) {
        Optional<Defensivos> defensivoOptional = defensivosRepository.findById(id);
        if (defensivoOptional.isPresent()) {
            Defensivos defensivoExistente = defensivoOptional.get();
            defensivoExistente.setNome(defensivoAtualizado.getNome());
            defensivoExistente.setCategoria(defensivoAtualizado.getCategoria());
            defensivoExistente.setDescricao(defensivoAtualizado.getDescricao());
            defensivoExistente.setQuantidade(defensivoAtualizado.getQuantidade());
            defensivoExistente.setFabricante(defensivoAtualizado.getFabricante());
            defensivoExistente.setPreco(defensivoAtualizado.getPreco());
            defensivosRepository.save(defensivoExistente); // Salva as alterações
        }
        return "redirect:/defensivos"; // Redireciona para a lista de defensivos
    }

    @GetMapping("/deletar/{id}")
    public String deletarDefensivo(@PathVariable Long id) {
        defensivosRepository.deleteById(id); // Deleta o defensivo
        return "redirect:/defensivos"; // Redireciona para a lista de defensivos
    }
    
 // Método para buscar defensivos por nome
    @GetMapping("/buscar")
    public String buscarDefensivos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false, defaultValue = "cards") String formato,
            Model model) {

        List<Defensivos> defensivos;

        if (nome == null || nome.trim().isEmpty()) {
            // Retorna todos os defensivos se o campo estiver vazio
            defensivos = defensivosRepository.findAll();
        } else {
            // Realiza a busca pelo nome
            defensivos = defensivosRepository.findByNomeContainingIgnoreCase(nome);
        }

        model.addAttribute("defensivos", defensivos);

        // Retorna o template correto com base no formato solicitado
        if ("tabelas".equalsIgnoreCase(formato)) {
            return "listarDefensivosTabelas"; // Template para exibir em tabelas
        } else {
            return "listarDefensivos"; // Template para exibir em cards
        }
    }

    
}
