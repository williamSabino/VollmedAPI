package com.medico.vollMed.domains.consulta;

import com.medico.vollMed.domains.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosConsultaDTO(Long id, Long medicoId, Long pacienteId , LocalDateTime data) {
}
