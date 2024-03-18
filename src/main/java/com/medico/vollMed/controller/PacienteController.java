package com.medico.vollMed.controller;

import com.medico.vollMed.domains.paciente.PacienteAtualizarDTO;
import com.medico.vollMed.domains.paciente.PacienteCadastroDTO;
import com.medico.vollMed.service.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity listarPacientes(Pageable pageable){
        return pacienteService.listarPacientes(pageable);
    }

    @PostMapping
    public ResponseEntity cadastrarPaciente(@Valid @RequestBody PacienteCadastroDTO pacienteCadastroDTO, UriComponentsBuilder uriBuilder){
        return pacienteService.cadastrarPaciente(pacienteCadastroDTO, uriBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@Valid @RequestBody PacienteAtualizarDTO pacienteAtualizarDTO){
        return pacienteService.atualizarPaciente(pacienteAtualizarDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarPaciente(@PathVariable Long id){
        return pacienteService.deletarPaciente(id);
    }


}
