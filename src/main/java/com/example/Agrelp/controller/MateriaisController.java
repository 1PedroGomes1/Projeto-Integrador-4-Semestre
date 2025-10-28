package com.example.Agrelp.controller;

import com.example.Agrelp.model.Materiais;
import com.example.Agrelp.repository.MateriaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/materiais")
public class MateriaisController {

    @Autowired
    private MateriaisRepository materiaisRepository;

    // Exibir o formulário para adicionar um novo material
    @GetMapping("/adicionar")
    public String mostrarFormulario() {
        return "formMateriais"; // Retorna a página do formulário
    }

    // Listar todos os materiais
    @GetMapping("/cards")
    public String listarMateriais(Model model) {
        List<Materiais> materiais = materiaisRepository.findAll();
        model.addAttribute("materiais", materiais);
        return "listarMateriais"; // Retorna a página com a lista de materiais
    }
    
    // Listar todos os materiais
    @GetMapping
    public String listarMateriaisTabelas(Model model) {
        List<Materiais> materiais = materiaisRepository.findAll();
        model.addAttribute("materiais", materiais);
        return "listarMateriaisTabelas"; // Retorna a página com a lista de materiais
    }
    

    // Adicionar um novo material
    @PostMapping
    public String adicionarMaterial(@ModelAttribute Materiais materiais) {
        materiaisRepository.save(materiais);
        return "redirect:/materiais"; // Redireciona para a página de listagem
    }

    // Exibir o formulário para editar um material
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Optional<Materiais> materialOptional = materiaisRepository.findById(id);
        if (materialOptional.isPresent()) {
            model.addAttribute("material", materialOptional.get());
            return "editarMateriais"; // Retorna o formulário de edição
        } else {
            return "redirect:/materiais"; // Redireciona se o material não for encontrado
        }
    }

    // Atualizar um material
    @PostMapping("/editar/{id}")
    public String atualizarMaterial(@PathVariable Long id, @ModelAttribute Materiais materialAtualizado) {
        Optional<Materiais> materialOptional = materiaisRepository.findById(id);
        if (materialOptional.isPresent()) {
            Materiais materialExistente = materialOptional.get();
            // Atualiza os dados
            materialExistente.setNome(materialAtualizado.getNome());
            materialExistente.setCategoria(materialAtualizado.getCategoria());
            materialExistente.setDescricao(materialAtualizado.getDescricao());
            materialExistente.setQuantidade(materialAtualizado.getQuantidade());
            materiaisRepository.save(materialExistente);
        }
        return "redirect:/materiais"; // Redireciona para a página de listagem
    }

    // Deletar um material
    @GetMapping("/deletar/{id}")
    public String deletarMaterial(@PathVariable Long id) {
        materiaisRepository.deleteById(id);
        return "redirect:/materiais"; // Redireciona para a página de listagem após deletar
    }
    
 // Método para buscar materiais por nome
    @GetMapping("/buscar")
    public String buscarMateriais(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false, defaultValue = "cards") String formato,
            Model model) {

        List<Materiais> materiais;

        if (nome == null || nome.trim().isEmpty()) {
            // Retorna todos os materiais se o campo estiver vazio
            materiais = materiaisRepository.findAll();
        } else {
            // Realiza a busca pelo nome
            materiais = materiaisRepository.findByNomeContainingIgnoreCase(nome);
        }

        model.addAttribute("materiais", materiais);

        // Retorna o template correto com base no formato solicitado
        if ("tabelas".equalsIgnoreCase(formato)) {
            return "listarMateriaisTabelas"; // Template para exibir em tabelas
        } else {
            return "listarMateriais"; // Template para exibir em cards
        }
    }

    
}
