package com.medico.vollMed.domains.paciente;

import com.medico.vollMed.domains.endereco.EnderecoDTO;

public record PacienteAtualizarDTO(Long id, String nome, String telefone, EnderecoDTO endereco) {
}
