package br.bonnasys.vaccines.app.listener;

import br.bonnasys.vaccines.app.listener.request.CreateVaccineRequest;
import br.bonnasys.vaccines.domain.enums.Json;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineUseCase;
import br.bonnasys.vaccines.infrastructure.annotation.CreateVaccineQueue;
import br.bonnasys.vaccines.infrastructure.properties.QueueProperties;
import br.bonnasys.vaccines.support.annotation.AmqpTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.TimeUnit;

import static br.bonnasys.vaccines.app.listener.CreateVaccineListener.LISTENER_ID;

@AmqpTest
class CreateVaccineListenerTest {

    @Autowired
    private TestRabbitTemplate testRabbitTemplate;

    @Autowired(required = false)
    private RabbitListenerTestHarness harness;

    @MockBean
    private CreateVaccineUseCase createVaccineUseCase;

    @Autowired
    @CreateVaccineQueue
    private QueueProperties queueProperties;

    @Test
    void givenValidRequestWhenListenerRetrieveMessageThenAssertFields() throws InterruptedException {
        final var message = new CreateVaccineRequest("HPV", "Anvisa");
        final var expectedMessage = Json.writeValueAsString(message);

        this.testRabbitTemplate.convertAndSend(queueProperties.getQueue(), expectedMessage);

        final var invocation = harness.getNextInvocationDataFor(LISTENER_ID, 1, TimeUnit.SECONDS);

        Assertions.assertNotNull(invocation);
        Assertions.assertNotNull(invocation.getArguments());

        final var actualMessage = (String) invocation.getArguments()[0];
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}