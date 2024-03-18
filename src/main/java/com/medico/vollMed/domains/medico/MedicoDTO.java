package com.medico.vollMed.domains.medico;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.medico.vollMed.domains.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Valid
@JsonIgnoreProperties
public record MedicoDTO(
        @NotBlank(message = "Atributo nome não pode ser vazio")
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @Valid
        @NotNull
        EnderecoDTO endereco
) {
}
