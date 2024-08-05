package br.bonnasys.vaccines.domain.usecase.vaccine.retrieve.search;

import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchVaccineUseCaseImpl implements SearchVaccineUseCase {
    private final VaccineRepository vaccineRepository;

    @Override
    public Page<Vaccine> execute(Pageable pageable) {
        return vaccineRepository.findAll(pageable);
    }
}
