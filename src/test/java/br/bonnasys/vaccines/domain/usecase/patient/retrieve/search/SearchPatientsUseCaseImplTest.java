package br.bonnasys.vaccines.domain.usecase.patient.retrieve.search;

import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import br.bonnasys.vaccines.support.builder.PatientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SearchPatientsUseCaseImplTest {

    @Autowired
    private SearchPatientsUseCase searchPatientsUseCase;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void check() {
        patientRepository.save(PatientBuilder.any().name("Jose").build());
        patientRepository.save(PatientBuilder.any().name("Italiano").build());
        patientRepository.save(PatientBuilder.any().name("Fernando").build());
        patientRepository.save(PatientBuilder.any().name("Padre").build());

        PageRequest patients = PageRequest.of(0, 10);
        SearchPatientsCommand command = new SearchPatientsCommand("alias", patients);
        searchPatientsUseCase.execute(command);
    }
}