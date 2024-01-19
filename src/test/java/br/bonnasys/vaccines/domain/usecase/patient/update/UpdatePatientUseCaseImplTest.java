package br.bonnasys.vaccines.domain.usecase.patient.update;


import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import br.bonnasys.vaccines.support.builder.PatientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
class UpdatePatientUseCaseImplTest {

    @Autowired
    private UpdatePatientUseCase updatePatientUseCase;

    @Autowired
    private PatientRepository repository;

    @Test
    void givenValidMapWhenCallsUpdatePatientUseCaseThenAssertFields() {
        Patient patient = PatientBuilder.any().build();
        Patient createdPatient = repository.save(patient);

        Map<String, Object> fields = new HashMap<>();
        String newName = "Henrique Tilapia";
        fields.put("name", newName);

        String patientId = createdPatient.getId();

        updatePatientUseCase.execute(patientId, fields);

        Patient retrievedPatient = repository.findById(patientId).orElseThrow();
        Assertions.assertNotEquals(patient.getName(), retrievedPatient.getName());
        Assertions.assertEquals(newName, retrievedPatient.getName());
    }

    @Test
    void givenInvalidMapWhenCallsUpdatePatientUseCaseThenShouldNotThrowsException() {
        Patient patient = PatientBuilder.any().name("Pedro José").build();
        Patient createdPatient = repository.save(patient);

        Map<String, Object> fields = new HashMap<>();
        String newName = "Pedro Aruba";
        String newPhone = "(00) 00000-0000";
        fields.put("name", newName);
        fields.put("idade", newName);
        fields.put("telefone", newName);
        fields.put("email", newName);
        fields.put("cellphone", newName);
        fields.put("phone", newPhone);

        String patientId = createdPatient.getId();

        updatePatientUseCase.execute(patientId, fields);

        Patient retrievedPatient = repository.findById(patientId).orElseThrow();
        Assertions.assertNotEquals(patient.getName(), retrievedPatient.getName());
        Assertions.assertEquals(newName, retrievedPatient.getName());
    }
}