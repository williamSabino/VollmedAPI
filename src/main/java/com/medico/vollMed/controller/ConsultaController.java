package com.medico.vollMed.controller;

import com.medico.vollMed.domains.consulta.DadosAgendamentoDTO;
import com.medico.vollMed.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity agendar(@Valid @RequestBody DadosAgendamentoDTO dadosAgendamentoDTO){
        return consultaService.agendar(dadosAgendamentoDTO);
    }
}
