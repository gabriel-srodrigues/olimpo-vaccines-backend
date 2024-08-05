package br.bonnasys.vaccines.app.rest.controller;

import br.bonnasys.vaccines.app.mapper.PatientMapper;
import br.bonnasys.vaccines.app.rest.PatientApi;
import br.bonnasys.vaccines.domain.usecase.PatientFacade;
import br.bonnasys.vaccines.support.annotation.ControllerTest;
import br.bonnasys.vaccines.support.builder.PatientBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest(controllers = PatientApi.class)
class PatientRestControllerTest {

    @MockBean
    private PatientFacade patientFacade;

    @MockBean
    private PatientMapper patientMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void dummy() throws Exception {

        Mockito.when(patientFacade.createPatientUseCase(Mockito.any()))
                .thenReturn(PatientBuilder.any().withId().build());

        final String payload = """
                {
                    "name": "Gabriel",
                    "phone": "9999999999",
                    "email": "gabriel.rodrigues@email.com",
                    "birthdate": "1999-07-08"
                }
                """;

        final var aRequest = MockMvcRequestBuilders
                .post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload);

        final var aResponse = this.mvc.perform(aRequest)
                .andDo(print());

        aResponse.andExpect(status().isCreated());
    }
}