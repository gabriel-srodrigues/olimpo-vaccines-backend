package br.bonnasys.vaccines.domain.usecase.patient.retrieve.search;

import br.bonnasys.vaccines.domain.model.Patient;
import org.springframework.data.domain.Page;

public interface SearchPatientsUseCase {

    Page<Patient> execute(SearchPatientsCommand command);
}
