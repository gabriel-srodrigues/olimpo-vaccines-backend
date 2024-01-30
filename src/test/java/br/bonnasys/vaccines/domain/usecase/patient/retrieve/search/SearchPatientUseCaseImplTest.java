package br.bonnasys.vaccines.domain.usecase.patient.retrieve.search;

import br.bonnasys.vaccines.domain.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SearchPatientUseCaseImplTest {

    @Autowired
    private SearchPatientUseCase searchPatientUseCase;

    @Test
    void givenValidPageableWhenCallsSearchPatientUseCaseThenReturnPatients() {
        Page<Patient> patientPage = searchPatientUseCase.execute(null, PageRequest.of(0, 2));
        Assertions.assertNotNull(patientPage);
    }

    @Test
    void givenACommandWhenCallsSearchPatientUseCaseThenReturnPatients() {
        Integer expectedContentSize = 2;
        Long expectedTotalElements = 2L;

        SearchPatientCommand command = new SearchPatientCommand("Ga", null);
        Page<Patient> patientPage = searchPatientUseCase.execute(command, PageRequest.of(0, 2));

        Assertions.assertNotNull(patientPage);
        Assertions.assertEquals(expectedContentSize, patientPage.getContent().size());
        Assertions.assertEquals(expectedTotalElements, patientPage.getTotalElements());
    }
}