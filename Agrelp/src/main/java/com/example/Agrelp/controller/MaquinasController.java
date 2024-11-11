package com.example.Agrelp.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Agrelp.model.Maquinas;
import com.example.Agrelp.repository.MaquinasRepository;

@Controller
@RequestMapping("/maquinas")
public class MaquinasController {

    @Autowired
    private MaquinasRepository maquinasRepository;

    @GetMapping("/adicionar")
    public String mostrarFormulario() {
        return "formMaquinas"; // Retorna o nome do arquivo HTML sem a extensão
    }

    @GetMapping
    public String listarMaquinas(Model model) {
        List<Maquinas> maquinas = maquinasRepository.findAll();
        model.addAttribute("maquinas", maquinas);
        return "listaMaquinasCard"; // Retorna o nome do arquivo HTML sem a extensão
    }

    @PostMapping
    public String adicionarMaquina(@ModelAttribute Maquinas maquinas, @RequestParam("imagem") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Verifica se o diretório uploads existe, se não existir, cria
                File uploadsDir = new File("src/main/resources/static/uploads");
                if (!uploadsDir.exists()) {
                    uploadsDir.mkdirs(); // Cria o diretório
                }

                // Salva a imagem
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadsDir.getAbsolutePath(), file.getOriginalFilename());
                Files.write(path, bytes);

                // Salva o caminho da imagem no banco de dados
                maquinas.setImagemUrl("/uploads/" + file.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace(); // Imprime o stack trace para depuração
                return "redirect:/maquinas/adicionar"; // Redireciona em caso de erro
            }
        }

        maquinasRepository.save(maquinas); // Salva a máquina no banco de dados
        return "redirect:/maquinas"; // Redireciona após sucesso
    }

    @GetMapping("/detalhes/{id}")
    public String mostrarDetalhes(@PathVariable Long id, Model model) {
        Maquinas maquina = maquinasRepository.findById(id).orElse(null);
        if (maquina != null) {
            model.addAttribute("maquina", maquina);
            return "detalhesMaquina"; // Página que exibe os detalhes
        }
        return "redirect:/maquinas"; // Redireciona caso a máquina não seja encontrada
    }

    // Método para exibir o formulário de edição
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Optional<Maquinas> maquinaOptional = maquinasRepository.findById(id);
        if (maquinaOptional.isPresent()) {
            model.addAttribute("maquina", maquinaOptional.get());
            return "editarMaquinas"; // Reutiliza o formulário para edição
        } else {
            return "redirect:/maquinas"; // Redireciona se o ID não for encontrado
        }
    }

    // Método para salvar as alterações da edição
    @PostMapping("/editar/{id}")
    public String atualizarMaquina(@PathVariable Long id, @ModelAttribute Maquinas maquinaAtualizada, @RequestParam("imagem") MultipartFile file) {
        Optional<Maquinas> maquinaOptional = maquinasRepository.findById(id);
        if (maquinaOptional.isPresent()) {
            Maquinas maquinaExistente = maquinaOptional.get();
            // Atualiza os dados
            maquinaExistente.setNome(maquinaAtualizada.getNome());
            maquinaExistente.setModelo(maquinaAtualizada.getModelo());
            maquinaExistente.setAnoFabricacao(maquinaAtualizada.getAnoFabricacao());
            maquinaExistente.setTipoMaquina(maquinaAtualizada.getTipoMaquina());
            maquinaExistente.setCapacidadeCombustivel(maquinaAtualizada.getCapacidadeCombustivel());
            maquinaExistente.setHorasTrabalhadas(maquinaAtualizada.getHorasTrabalhadas());
            maquinaExistente.setDataUltimaManutencao(maquinaAtualizada.getDataUltimaManutencao());
            maquinaExistente.setNumeroSerie(maquinaAtualizada.getNumeroSerie());
            maquinaExistente.setDescricao(maquinaAtualizada.getDescricao());

            // Se uma nova imagem foi enviada
            if (!file.isEmpty()) {
                try {
                    // Verifica se o diretório uploads existe, se não existir, cria
                    File uploadsDir = new File("src/main/resources/static/uploads");
                    if (!uploadsDir.exists()) {
                        uploadsDir.mkdirs(); // Cria o diretório
                    }

                    // Salva a nova imagem
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(uploadsDir.getAbsolutePath(), file.getOriginalFilename());
                    Files.write(path, bytes);

                    // Atualiza o caminho da imagem
                    maquinaExistente.setImagemUrl("/uploads/" + file.getOriginalFilename());
                } catch (Exception e) {
                    e.printStackTrace(); // Imprime o stack trace para depuração
                    return "redirect:/maquinas/editar/" + id; // Redireciona em caso de erro
                }
            }

            maquinasRepository.save(maquinaExistente); // Salva as alterações no banco de dados
        }
        return "redirect:/maquinas"; // Redireciona após sucesso
    }

    // Método para deletar uma máquina
    @GetMapping("/deletar/{id}")
    public String deletarMaquina(@PathVariable Long id) {
        maquinasRepository.deleteById(id);
        return "redirect:/maquinas"; // Redireciona para a lista após deletar
    }
    
 // Método para buscar ferramentas por nome
    @GetMapping("/buscar")
    public String buscarMaquinas(@RequestParam(required = false) String nome, Model model) {
        List<Maquinas> maquinas;
        
        if (nome == null || nome.trim().isEmpty()) {
            // Retorna todas as ferramentas se o campo estiver vazio
        	maquinas = maquinasRepository.findAll();
        } else {
            // Realiza a busca pelo nome
        	maquinas = maquinasRepository.findByNomeContainingIgnoreCase(nome);
        }
        
        model.addAttribute("maquinas", maquinas);
        return "listaMaquinasCard"; // Exibe a lista (completa ou filtrada)
    }
    
}
