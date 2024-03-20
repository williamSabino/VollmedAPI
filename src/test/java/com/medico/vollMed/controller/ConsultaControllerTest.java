package com.medico.vollMed.controller;

import com.medico.vollMed.domains.consulta.DadosAgendamentoDTO;
import com.medico.vollMed.domains.medico.Especialidade;
import com.medico.vollMed.service.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class ConsultaControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosAgendamentoDTO> dadosAgendamentoDTOJacksonTester;
    @MockBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("Teste error 400")
    void agendarCenario1() throws Exception {
        var response = mvc.perform(post("/consulta"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Teste status 200")
    void agendarCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(2);
        var response = mvc.perform(
                post("/consulta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoDTOJacksonTester.write(
                                new DadosAgendamentoDTO(2l,2l,data, Especialidade.CARDIOLOGIA)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}