package br.bonnasys.vaccines.domain.usecase.patient.retrieve.get;

import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import br.bonnasys.vaccines.support.builder.PatientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class GetPatientByIdUseCaseImplTest {

    @Autowired
    private GetPatientByIdUseCase getPatientByIdUseCase;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void check() {
        Patient patient = PatientBuilder.any().build();
        String id = patientRepository.save(patient).getId();

        Patient retrievedPatient = getPatientByIdUseCase.execute(id);

        Assertions.assertNotNull(retrievedPatient);
    }
}