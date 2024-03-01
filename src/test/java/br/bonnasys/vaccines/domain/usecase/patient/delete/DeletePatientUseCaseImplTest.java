package br.bonnasys.vaccines.domain.usecase.patient.delete;

import br.bonnasys.vaccines.domain.exception.PatientNotFoundException;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import br.bonnasys.vaccines.support.annotation.UnitTest;
import br.bonnasys.vaccines.support.builder.PatientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@UnitTest
class DeletePatientUseCaseImplTest {

    @Autowired
    private DeletePatientUseCase deletePatientUseCase;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void givenValidPatientIdWhenCallsDeletePatientUseCaseThenShouldNotFail() {
        Patient patient = PatientBuilder.any().build();

        Patient createdPatient = patientRepository.save(patient);
        String patientId = createdPatient.getId();

        Assertions.assertDoesNotThrow(() -> deletePatientUseCase.execute(patientId));
    }

    @Test
    void givenInValidPatientIdWhenCallsDeletePatientUseCaseThen() {
        String patientId = "03104608-449e-4bb4-aeb8-f80188daee6b";
        PatientNotFoundException ex = Assertions.assertThrows(
                PatientNotFoundException.class,
                () -> deletePatientUseCase.execute(patientId));
        Assertions.assertEquals("Patient not found", ex.getMessage());

    }
}