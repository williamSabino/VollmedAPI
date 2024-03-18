package com.medico.vollMed.domains.consulta.validacoes;

import com.medico.vollMed.domains.consulta.DadosAgendamentoDTO;
import com.medico.vollMed.infra.exception.ValidarException;
import com.medico.vollMed.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoDisponivel implements ValidacoesConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO) {
        var medicoDisponivel = consultaRepository.existsByMedicoIdAndData(dadosAgendamentoDTO.idMedico(),dadosAgendamentoDTO.data());
        if(medicoDisponivel){
            throw new ValidarException("Medico ja possui consulta neste mesmo horario");
        }
    }
}
