package com.example.Agrelp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Agrelp.repository.DefensivosRepository;
import com.example.Agrelp.repository.FerramentasRepository;
import com.example.Agrelp.repository.MaquinasRepository;
import com.example.Agrelp.repository.MateriaisRepository;
import com.example.Agrelp.repository.SementesRepository;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private MaquinasRepository maquinasRepository;

    @Autowired
    private FerramentasRepository ferramentasRepository;

    @Autowired
    private SementesRepository sementesRepository;

    @Autowired
    private MateriaisRepository materiaisRepository;

    @Autowired
    private DefensivosRepository defensivosRepository;

    @GetMapping
    public String mostrarDashboard(Model model) {
        long quantidadeMaquinas     = maquinasRepository.count();
        long quantidadeFerramentas  = ferramentasRepository.count();
        long quantidadeSementes     = sementesRepository.count();
        long quantidadeMateriais    = materiaisRepository.count();
        long quantidadeDefensivos   = defensivosRepository.count();

        model.addAttribute("quantidadeMaquinas", quantidadeMaquinas);
        model.addAttribute("quantidadeFerramentas", quantidadeFerramentas);
        model.addAttribute("quantidadeSementes", quantidadeSementes);
        model.addAttribute("quantidadeMateriais", quantidadeMateriais);
        model.addAttribute("quantidadeDefensivos", quantidadeDefensivos);

        // ===== Dados dos gráficos (exemplo). Se quiser, troque por dados reais do seu banco. =====
        model.addAttribute("meses", List.of("Jan","Fev","Mar","Abr","Mai","Jun","Jul"));
        model.addAttribute("leadTimeDias", List.of(2.20, 2.05, 1.90, 1.78, 1.79, 1.60, 1.45));

        model.addAttribute("regioes", List.of("US","EU","BR","APAC"));
        model.addAttribute("onTimePorRegiao", List.of(98, 96, 95, 97));

        return "dashboard";
    }

    // ==============================
    // ======  ENDPOINTS JSON  ======
    // ==============================

    /** Gráfico 1: Evolução Mensal · Lead Time (dias) */
    @GetMapping("/api/lead-time")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getLeadTimeSeries() {
        Map<String, Object> payload = Map.of(
            "labels", List.of("Jan","Fev","Mar","Abr","Mai","Jun","Jul"),
            "data",   List.of(2.20, 2.05, 1.90, 1.78, 1.79, 1.60, 1.45),
            "series", "Lead Time (dias)"
        );
        return ResponseEntity.ok(payload);
    }

    /** Gráfico 2: On-Time Delivery por Região (%) */
    @GetMapping("/api/on-time-region")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getOnTimeByRegion() {
        Map<String, Object> payload = Map.of(
            "labels", List.of("US","EU","BR","APAC"),
            "data",   List.of(98, 96, 95, 97),
            "series", "On-Time (%)"
        );
        return ResponseEntity.ok(payload);
    }
}
