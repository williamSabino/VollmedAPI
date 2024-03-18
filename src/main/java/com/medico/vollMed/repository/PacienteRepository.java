package com.medico.vollMed.repository;

import com.medico.vollMed.domains.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
}
