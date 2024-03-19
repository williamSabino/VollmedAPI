package com.medico.vollMed.repository;

import com.medico.vollMed.domains.consulta.Consulta;
import com.medico.vollMed.domains.endereco.EnderecoDTO;
import com.medico.vollMed.domains.medico.Especialidade;
import com.medico.vollMed.domains.medico.Medico;
import com.medico.vollMed.domains.medico.MedicoDTO;
import com.medico.vollMed.domains.paciente.Paciente;
import com.medico.vollMed.domains.paciente.PacienteCadastroDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Testes de medico disponivel deve ser nulo")
    void pegarMedicoAleatorioDisponivelCenario1() {
        var dataProximaSegunda = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var medico = criarMedico("william", "will147@gmail.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente = criarPaciente("andresa", "desa159@gmail.com", "8157826", "321654", dadosEndereco());
        criarConsulta(medico,paciente,dataProximaSegunda);
        var medicolivre = medicoRepository.pegarMedicoAleatorioDisponivel(medico.getEspecialidade(), dataProximaSegunda);
        assertThat(medicolivre).isNull();
    }

    @Test
    @DisplayName("Testes de medico disponivel deve devolver um verdadeiro")
    void pegarMedicoAleatorioDisponivelCenario2() {
        var dataProximaSegunda = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico = criarMedico("william", "will147@gmail.com", "123456", Especialidade.CARDIOLOGIA);

        var medicolivre = medicoRepository.pegarMedicoAleatorioDisponivel(medico.getEspecialidade(), dataProximaSegunda);
        assertThat(medicolivre).isEqualTo(medico);
    }


    //metodos

    private void criarConsulta(Medico medico, Paciente paciente, LocalDateTime data){
        em.persist(new Consulta(null, medico,paciente,data));
    }

    private Medico criarMedico(String nome, String email, String crm, Especialidade especialidade){
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente criarPaciente(String nome, String  email,String telefone, String cpf, EnderecoDTO endereco){
        var paciente = new Paciente(dadosPaciente(nome,email,telefone,cpf,endereco));
        em.persist(paciente);
        return paciente;
    }


    //dados criados
    private MedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicoDTO(
                nome,
                email,
                "82748007",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private PacienteCadastroDTO dadosPaciente(String nome, String  email,String telefone,String cpf,EnderecoDTO endereco){
        return new PacienteCadastroDTO(
                nome,
                email,
                telefone,
                cpf,
                endereco);
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                "pe",
                55120000
        );
}
}