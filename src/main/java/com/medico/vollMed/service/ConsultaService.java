package com.medico.vollMed.service;

import com.medico.vollMed.domains.consulta.Consulta;
import com.medico.vollMed.domains.consulta.DadosAgendamentoDTO;
import com.medico.vollMed.domains.consulta.validacoes.ValidacoesConsulta;
import com.medico.vollMed.domains.medico.Medico;
import com.medico.vollMed.infra.exception.ValidarException;
import com.medico.vollMed.repository.ConsultaRepository;
import com.medico.vollMed.repository.MedicoRepository;
import com.medico.vollMed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private List<ValidacoesConsulta> validadores;

    public ResponseEntity agendar(DadosAgendamentoDTO dadosAgendamentoDTO) {
        if(!pacienteRepository.existsById(dadosAgendamentoDTO.idPaciente())) throw new ValidarException("Id do paciente Não é valido");
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoDTO.idPaciente());
        var medico = buscarMedicoDisponivel(dadosAgendamentoDTO);
        validadores.forEach(v -> v.validar(dadosAgendamentoDTO));
        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoDTO.data());
        repository.save(consulta);
        return ResponseEntity.ok().build();
    }

    private Medico buscarMedicoDisponivel(DadosAgendamentoDTO dadosAgendamentoDTO) {
        if(medicoRepository.existsById(dadosAgendamentoDTO.idMedico())){
            return medicoRepository.getReferenceById(dadosAgendamentoDTO.idMedico());
        }

        if(dadosAgendamentoDTO.especialidade() == null){
            throw new ValidarException("Especialização necessaria, caso o medico não seja atribuido !");
        }

        return medicoRepository.pegarMedicoAleatorioDisponivel(dadosAgendamentoDTO.especialidade(),dadosAgendamentoDTO.data());
    }
}
