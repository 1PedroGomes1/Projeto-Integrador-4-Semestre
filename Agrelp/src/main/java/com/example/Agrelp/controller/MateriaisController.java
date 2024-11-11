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
    @GetMapping
    public String listarMateriais(Model model) {
        List<Materiais> materiais = materiaisRepository.findAll();
        model.addAttribute("materiais", materiais);
        return "listarMateriais"; // Retorna a página com a lista de materiais
    }

    // Adicionar um novo material
    @PostMapping
    public String adicionarMaterial(@ModelAttribute Materiais materiais) {
        materiaisRepository.save(materiais);
        return "redirect:/materiais"; // Redireciona para a página de listagem
    }

    // Exibir o formulário para editar um material
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable("id") Long id, Model model) {
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
    public String atualizarMaterial(@PathVariable("id") Long id, @ModelAttribute Materiais materialAtualizado) {
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
    public String deletarMaterial(@PathVariable("id") Long id) {
        materiaisRepository.deleteById(id);
        return "redirect:/materiais"; // Redireciona para a página de listagem após deletar
    }
}