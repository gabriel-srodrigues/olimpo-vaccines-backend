package br.bonnasys.vaccines.domain.usecase.vaccine.retrieve.search;

import br.bonnasys.vaccines.domain.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchVaccineUseCase {

    Page<Vaccine> execute(Pageable pageable);
}
