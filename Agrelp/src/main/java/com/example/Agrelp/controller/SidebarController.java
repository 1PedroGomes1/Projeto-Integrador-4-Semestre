package com.example.Agrelp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SidebarController {
	
	@GetMapping("/estoque")
    public String exibirEstoque() {
        // Retorna a página que inclui o fragmento da sidebar
        return "estoque"; // Nome do arquivo HTML (sem extensão)
    }
	
	@GetMapping("/bemvindo")
    public String exibirIndex() {
        // Retorna a página que inclui o fragmento da sidebar
        return "index"; // Nome do arquivo HTML (sem extensão)
    }
	
}
	
