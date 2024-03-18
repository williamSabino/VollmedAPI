package com.medico.vollMed.domains.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private Integer cep;

    public Endereco(EnderecoDTO endereco) {
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
    }

    public void atualizar(EnderecoDTO endereco) {
        if(endereco.bairro() != null){
            this.bairro = endereco.bairro();
        }
        if(endereco.uf() != null){
            this.uf = endereco.uf();
        }
        if(endereco.cep() != null){
            this.cep = endereco.cep();
        }
        if(endereco.cidade() != null){
            this.cidade = endereco.cidade();
        }
        if(endereco.numero() != null){
            this.numero = endereco.numero();
        }
        if(endereco.logradouro() != null){
            this.logradouro = endereco.logradouro();
        }
        if(endereco.complemento() != null){
            this.complemento = endereco.complemento();
        }

    }
}
