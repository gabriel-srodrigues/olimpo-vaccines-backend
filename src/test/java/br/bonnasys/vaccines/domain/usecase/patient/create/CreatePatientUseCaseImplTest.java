package br.bonnasys.vaccines.domain.usecase.patient.create;

import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
class CreatePatientUseCaseImplTest {

    @Autowired
    private CreatePatientUseCase createPatientUseCase;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void givenValidCommandWhenCallsCreatePatientUseCaseThenAssertFields() {
        String name = "Rick Dos Santos Rodrigues Paz Alves de Souza Sanches";
        String email = "rick.sanches@email.com";
        String phone = "(99) 99999-9999";
        LocalDate now = LocalDate.now();
        LocalDate birthdate = now
                .minusDays(12)
                .minusMonths(1)
                .minusYears(45);

        CreatePatientCommand command = new CreatePatientCommand(name, phone, email, birthdate);

        Patient patient = createPatientUseCase.execute(command);
        String patientId = patient.getId();

        Patient createdPatient = patientRepository.findById(patientId).orElseThrow();

        Assertions.assertEquals(name, createdPatient.getName());
        Assertions.assertEquals(phone, createdPatient.getPhone());
        Assertions.assertEquals(email, createdPatient.getEmail());
        Assertions.assertEquals(birthdate, createdPatient.getBirthdate());

        Assertions.assertTrue(createdPatient.getHistory().isEmpty());

        Assertions.assertNull(createdPatient.getUpdatedAt());
        Assertions.assertNotNull(createdPatient.getCreatedAt());
    }
}