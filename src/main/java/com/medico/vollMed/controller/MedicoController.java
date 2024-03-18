package com.medico.vollMed.controller;

import com.medico.vollMed.domains.medico.MedicoDTO;
import com.medico.vollMed.domains.medico.MedicoListagemDTO;
import com.medico.vollMed.domains.medico.Medico;
import com.medico.vollMed.service.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medico")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedicos(@Valid @RequestBody MedicoDTO medicoDTO, UriComponentsBuilder uriBuilder){
        var medico = new Medico(medicoDTO);
        medicoService.cadastrarMedico(medicoDTO);
        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(medico);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListagemDTO>> listarMedicos(Pageable pageable){
        return medicoService.listarMedicos(pageable);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedicos(@Valid @RequestBody MedicoListagemDTO medicoListagemDTO){
        return medicoService.atualizarMedicos(medicoListagemDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id){
        return medicoService.deletarMedico(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id){
        return medicoService.detalharMedico(id);
    }
}
