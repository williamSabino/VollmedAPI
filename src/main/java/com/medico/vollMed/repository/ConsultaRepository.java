package com.medico.vollMed.repository;

import com.medico.vollMed.domains.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime horarioDeAbertura, LocalDateTime horarioFechamento);

    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);
}
