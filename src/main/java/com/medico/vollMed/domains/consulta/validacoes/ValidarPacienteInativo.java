package com.medico.vollMed.domains.consulta.validacoes;

import com.medico.vollMed.domains.consulta.DadosAgendamentoDTO;
import com.medico.vollMed.infra.exception.ValidarException;
import com.medico.vollMed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteInativo implements ValidacoesConsulta {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Override
    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO) {
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoDTO.idPaciente());
        if (!paciente.isAtivo()){
            throw new ValidarException("Paciente inativo !");
        }
    }
}
