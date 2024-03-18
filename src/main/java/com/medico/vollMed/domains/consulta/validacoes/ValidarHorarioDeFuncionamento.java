package com.medico.vollMed.domains.consulta.validacoes;

import com.medico.vollMed.domains.consulta.DadosAgendamentoDTO;
import com.medico.vollMed.infra.exception.ValidarException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioDeFuncionamento implements ValidacoesConsulta {

    @Override
    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO) {
        var domingo = dadosAgendamentoDTO.data().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioDeAbertura = dadosAgendamentoDTO.data().getHour() < 7;
        var horarioFechamento = dadosAgendamentoDTO.data().getHour() > 19;

        if(domingo || horarioFechamento || horarioDeAbertura){
            throw new ValidarException("Fora do horario de funcionamento da clinica");
        }
    }
}
