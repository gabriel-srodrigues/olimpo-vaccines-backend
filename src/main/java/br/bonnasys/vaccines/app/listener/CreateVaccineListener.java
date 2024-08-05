package br.bonnasys.vaccines.app.listener;

import br.bonnasys.vaccines.app.listener.request.CreateVaccineRequest;
import br.bonnasys.vaccines.domain.enums.Json;
import br.bonnasys.vaccines.domain.model.VaccineRegistration;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineCommand;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class CreateVaccineListener {
    public static Integer count =0;
    public static final String LISTENER_ID = "createVaccineListener";
    public static final String PATIENT_LISTENER_ID = "vaccinationPatientListener";
    private final CreateVaccineUseCase createVaccineUseCase;

    @RabbitListener(id = LISTENER_ID, queues = "${amqp.queues.create-vaccines.queue}")
    public void onCreateVaccine(@Payload final String message) {
        CreateVaccineRequest aRequest = Json.readValue(message, CreateVaccineRequest.class);
        CreateVaccineCommand command = new CreateVaccineCommand(aRequest.name(), aRequest.producer());
        createVaccineUseCase.execute(command);
    }

    @RabbitListener(id = PATIENT_LISTENER_ID, queues = "${amqp.queues.vaccinate-patient.queue}")
    public void onVaccinatePatient(@Payload final String message) {
        Message aRequest = Json.readValue(message, Message.class);
        count++;
        log.info("""
                /                                                 %d
                =====================================================
                NOVA SOLICITAÇÃO DE VACINAÇÃO:             21/03/2024
                =====================================================
                PACIENTE: %s
                =====================================================
                VACINA: %s
                =====================================================
                CENTRO DE SAÚDE: %s
                =====================================================
                """.formatted(
                        count,
                aRequest.patientName(),
                aRequest.vaccineName(),
                aRequest.healthCenterName()));
    }


    public record Message(String patientName, String vaccineName, String healthCenterName) {}
}
