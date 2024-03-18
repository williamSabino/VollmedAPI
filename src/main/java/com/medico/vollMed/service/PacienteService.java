package com.medico.vollMed.service;

import com.medico.vollMed.domains.paciente.Paciente;
import com.medico.vollMed.domains.paciente.PacienteAtualizarDTO;
import com.medico.vollMed.domains.paciente.PacienteCadastroDTO;
import com.medico.vollMed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public ResponseEntity listarPacientes(Pageable pageable) {
        var listaPacientes = repository.findAll(pageable);
        return ResponseEntity.ok(listaPacientes);
    }

    public ResponseEntity cadastrarPaciente(PacienteCadastroDTO pacienteCadastroDTO, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(pacienteCadastroDTO);
        repository.save(paciente);
        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    public ResponseEntity atualizarPaciente(PacienteAtualizarDTO pacienteAtualizarDTO) {
        var paciente = repository.getReferenceById(pacienteAtualizarDTO.id());
        paciente.atualizar(pacienteAtualizarDTO);
        return ResponseEntity.ok(pacienteAtualizarDTO);
    }

    public ResponseEntity deletarPaciente(Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.deletar();
        return ResponseEntity.noContent().build();
    }
}
