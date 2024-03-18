package com.medico.vollMed.domains.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.medico.vollMed.domains.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoDTO(

        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade
) {
}
