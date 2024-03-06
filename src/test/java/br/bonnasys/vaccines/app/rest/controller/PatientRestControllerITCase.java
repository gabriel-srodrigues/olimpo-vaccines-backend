package br.bonnasys.vaccines.app.rest.controller;

import br.bonnasys.vaccines.domain.repository.PatientRepository;
import br.bonnasys.vaccines.support.annotation.E2ETest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@E2ETest
@Testcontainers
class PatientRestControllerITCase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository patientRepository;

    @Container
    private static final MySQLContainer<?> MYSQL_CONTAINER = new MySQLContainer<>("mysql:8.0.32")
            .withUsername("root")
            .withPassword("root")
            .withDatabaseName("vaccines");

    @Container
    private static final RabbitMQContainer RABBITMQ
            = new RabbitMQContainer("rabbitmq:3.7.25-management-alpine")
            .withAdminPassword("123456");

    @DynamicPropertySource
    private static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("mysql.port", () -> MYSQL_CONTAINER.getMappedPort(3306));
        registry.add("amqp.port", () -> RABBITMQ.getMappedPort(5672));
        registry.add("amqp.host", () -> "localhost");
        registry.add("amqp.password", () -> "123456");
        registry.add("amqp.username", () -> "guest");

    }

    @Test
    void givenValidPayloadWhenCallsCreatePatientThenAssertFields() throws Exception {
        Assertions.assertTrue(MYSQL_CONTAINER.isRunning());
        Assertions.assertEquals(4, patientRepository.count());

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

        Assertions.assertEquals(5, patientRepository.count());
    }


}