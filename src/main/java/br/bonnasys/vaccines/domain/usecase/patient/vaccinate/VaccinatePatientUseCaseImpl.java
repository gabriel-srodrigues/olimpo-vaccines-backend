package br.bonnasys.vaccines.domain.usecase.patient.vaccinate;

import br.bonnasys.vaccines.app.graphql.response.HealthCenterResponse;
import br.bonnasys.vaccines.domain.enums.Json;
import br.bonnasys.vaccines.domain.exception.HealthCenterNotFoundException;
import br.bonnasys.vaccines.domain.exception.IllegalVaccinationException;
import br.bonnasys.vaccines.domain.exception.PatientNotFoundException;
import br.bonnasys.vaccines.domain.exception.VaccineNotFoundException;
import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.model.VaccineRegistration;
import br.bonnasys.vaccines.domain.repository.HealthCenterRepository;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import br.bonnasys.vaccines.domain.repository.VaccineRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VaccinatePatientUseCaseImpl implements VaccinatePatientUseCase {
    private final VaccineRepository vaccineRepository;
    private final HealthCenterRepository healthCenterRepository;
    private final PatientRepository patientRepository;
    private final RabbitTemplate template;

    @Override
    public Patient execute(VaccinatePatientCommand command) {
        var patient = patientRepository.findById(command.patientId())
                .orElseThrow(PatientNotFoundException::new);

        var vaccine = vaccineRepository.findById(command.vaccineId())
                .orElseThrow(VaccineNotFoundException::new);

        var healthCenter = healthCenterRepository.findById(command.healthCenterId())
                .orElseThrow(HealthCenterNotFoundException::new);

        Integer actualAmount = healthCenter.getStock().get(vaccine);
        boolean hasVaccineAssociated = actualAmount != null;
        if (!hasVaccineAssociated) {
            throw new IllegalVaccinationException("Vacina não associada ao centro de sáude!!!");
        }

        boolean hasVaccineAvailable = actualAmount > 0;
        if (!hasVaccineAvailable) {
            throw new IllegalVaccinationException("Vacina não disponivel no centro de sáude!!!");
        }

        healthCenterRepository.save(healthCenter);

        VaccineRegistration vaccineRegistration = VaccineRegistration.from(patient, vaccine, healthCenter);
        patient.getHistory().add(vaccineRegistration);

        actualAmount -= 1;
        healthCenter.getStock().put(vaccine, actualAmount);

        Patient actualPatient = patientRepository.save(patient);

        var message = new Message(patient.getName(), vaccine.getName(), healthCenter.getName());
        template.convertAndSend("patients.events", "patient.vaccinate", Json.writeValueAsString(message));

        return actualPatient;

    }

    public record Message(String patientName, String vaccineName, String healthCenterName) {}

}
