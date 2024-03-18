package com.medico.vollMed.domains.consulta.validacoes;

import com.medico.vollMed.domains.consulta.DadosAgendamentoDTO;
import com.medico.vollMed.infra.exception.ValidarException;
import com.medico.vollMed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoInativo implements ValidacoesConsulta{
    @Autowired
    private MedicoRepository medicoRepository;
    @Override
    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO) {
        var medico = medicoRepository.getReferenceById(dadosAgendamentoDTO.idMedico());
        if(!medico.getAtivo()){
            throw new ValidarException("Medico inativo !");
        }
    }
}
