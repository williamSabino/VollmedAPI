package com.medico.vollMed.domains.consulta.validacoes;

import com.medico.vollMed.domains.consulta.DadosAgendamentoDTO;
import com.medico.vollMed.infra.exception.ValidarException;
import com.medico.vollMed.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarVariasConsultasMesmoPaciente implements ValidacoesConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO) {
        var horarioDeAbertura = dadosAgendamentoDTO.data().withHour(7);
        var horarioFechamento = dadosAgendamentoDTO.data().withHour(19);
        var paciente = consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoDTO.idPaciente(),horarioDeAbertura, horarioFechamento);

        if(paciente){
            throw new ValidarException("Paciente ja possui consulta nessa mesma data");
        }
    }
}
