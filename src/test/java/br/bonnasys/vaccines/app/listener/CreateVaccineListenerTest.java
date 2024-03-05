package br.bonnasys.vaccines.app.listener;

import br.bonnasys.vaccines.app.listener.request.CreateVaccineRequest;
import br.bonnasys.vaccines.domain.enums.Json;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineUseCase;
import br.bonnasys.vaccines.infrastructure.annotation.VaccineCreateQueue;
import br.bonnasys.vaccines.infrastructure.properties.QueueProperties;
import br.bonnasys.vaccines.support.annotation.AmqpTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.TimeUnit;

@AmqpTest
class CreateVaccineListenerTest {

    @Autowired
    private TestRabbitTemplate rabbitTemplate;

    @Autowired(required = false)
    private RabbitListenerTestHarness harness;

    @MockBean
    private CreateVaccineUseCase createVaccineUseCase;

    @Autowired
    @VaccineCreateQueue
    private QueueProperties queueProperties;

    @Test
    public void givenValidRequestWhenListenerRetrieveMessageThenAssertFields() throws InterruptedException {
        final var message = new CreateVaccineRequest("HPV", "Anvisa");
        final var expectedMessage = Json.writeValueAsString(message);

        this.rabbitTemplate.convertAndSend(queueProperties.getQueue(), expectedMessage);

        final var invocationData =
                harness.getNextInvocationDataFor(CreateVaccineListener.LISTENER_ID, 1, TimeUnit.SECONDS);

        Assertions.assertNotNull(invocationData);
        Assertions.assertNotNull(invocationData.getArguments());

        final var actualMessage = (String) invocationData.getArguments()[0];
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void givenInvalidRequestWhenListenerRetrieveMessageThenAssertFields() throws InterruptedException {
        final var expectedMessage = """
                {
                    "vaccine_name": "HPVosk",
                    "producer_name": "PRODu"
                }
                """;

        this.rabbitTemplate.convertAndSend(queueProperties.getQueue(), expectedMessage);

        final var invocationData =
                harness.getNextInvocationDataFor(CreateVaccineListener.LISTENER_ID, 1, TimeUnit.SECONDS);

        Assertions.assertNotNull(invocationData);
        Assertions.assertNotNull(invocationData.getArguments());

        final var actualMessage = (String) invocationData.getArguments()[0];
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}