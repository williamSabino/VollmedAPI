package com.medico.vollMed.domains.paciente;

import com.medico.vollMed.domains.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Paciente(PacienteCadastroDTO pacienteCadastroDTO) {
        this.ativo = true;
        this.nome = pacienteCadastroDTO.nome();
        this.cpf = pacienteCadastroDTO.cpf();
        this.email = pacienteCadastroDTO.email();
        this.telefone = pacienteCadastroDTO.telefone();
        this.endereco = new Endereco(pacienteCadastroDTO.endereco());
    }

    public void atualizar(PacienteAtualizarDTO pacienteAtualizarDTO) {
        if(pacienteAtualizarDTO.nome() != null){
            this.nome = pacienteAtualizarDTO.nome();
        }
        if(pacienteAtualizarDTO.telefone() != null){
            this.telefone = pacienteAtualizarDTO.telefone();
        }
        if(pacienteAtualizarDTO.endereco() != null){
            this.endereco.atualizar(pacienteAtualizarDTO.endereco());
        }
    }

    public void deletar() {
        this.ativo = false;
    }
}
