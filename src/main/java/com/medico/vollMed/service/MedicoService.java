package com.medico.vollMed.service;

import com.medico.vollMed.domains.medico.MedicoDTO;
import com.medico.vollMed.domains.medico.MedicoListagemDTO;
import com.medico.vollMed.domains.medico.Medico;
import com.medico.vollMed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;

    public void cadastrarMedico(MedicoDTO medico){
        medicoRepository.save(new Medico(medico));
    }

    public ResponseEntity<Page<MedicoListagemDTO>> listarMedicos(Pageable pageable) {
        var page = medicoRepository.findAllByAtivoTrue(pageable).map(MedicoListagemDTO::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity atualizarMedicos(MedicoListagemDTO medicoListagemDTO) {
        var medico = medicoRepository.getReferenceById(medicoListagemDTO.id());
        medico.atualizar(medicoListagemDTO);
        return ResponseEntity.ok(medico);
    }

    public ResponseEntity deletarMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.deletar();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity detalharMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoListagemDTO(medico));
    }
}
