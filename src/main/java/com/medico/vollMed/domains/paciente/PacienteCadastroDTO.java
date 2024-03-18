package com.medico.vollMed.domains.paciente;

import com.medico.vollMed.domains.endereco.EnderecoDTO;

public record PacienteCadastroDTO(
        String nome,
        String  email,
        String telefone,
        String cpf,
        EnderecoDTO endereco
) {
}
