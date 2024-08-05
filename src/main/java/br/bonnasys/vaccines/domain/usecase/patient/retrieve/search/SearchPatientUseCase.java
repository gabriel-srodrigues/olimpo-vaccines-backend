package br.bonnasys.vaccines.domain.usecase.patient.retrieve.search;

import br.bonnasys.vaccines.domain.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface SearchPatientUseCase {

    Page<Patient> execute(SearchPatientCommand command, Pageable page);

}
