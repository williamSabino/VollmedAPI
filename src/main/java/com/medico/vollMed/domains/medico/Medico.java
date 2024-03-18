package com.medico.vollMed.domains.medico;

import com.medico.vollMed.domains.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = ("id"))
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(MedicoDTO serie) {
        this.ativo = true;
        this.nome = serie.nome();
        this.email = serie.email();
        this.crm = serie.crm();
        this.endereco = new Endereco(serie.endereco());
        this.especialidade = serie.especialidade();
        this.telefone = serie.telefone();
    }


    public void atualizar(MedicoListagemDTO medicoListagemDTO) {
        if(medicoListagemDTO.nome() != null){
            this.nome = medicoListagemDTO.nome();
        }
        if(medicoListagemDTO.email() != null){
            this.email = medicoListagemDTO.email();
        }
        if(medicoListagemDTO.telefone() != null){
            this.telefone = medicoListagemDTO.telefone();
        }
    }

    public void deletar() {
        this.ativo = false;
    }
}
