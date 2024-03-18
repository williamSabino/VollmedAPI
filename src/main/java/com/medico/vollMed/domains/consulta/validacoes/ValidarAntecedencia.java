package com.medico.vollMed.domains.consulta.validacoes;

import com.medico.vollMed.domains.consulta.DadosAgendamentoDTO;
import com.medico.vollMed.infra.exception.ValidarException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarAntecedencia implements ValidacoesConsulta{
    @Override
    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO) {
        var agora = LocalDateTime.now();
        var dataConsulta = dadosAgendamentoDTO.data();
        var diferenca = Duration.between(agora, dataConsulta).toMinutes();

        if(diferenca < 30){
            throw new ValidarException("Agendamento so permitido com mais de 30 minutos de antecedencia");
        }
    }
}
