package br.bonnasys.vaccines.domain.usecase.vaccine.retrieve.get;

import br.bonnasys.vaccines.domain.exception.VaccineNotFoundException;
import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetVaccineUseCaseImpl implements GetVaccineUseCase {
    private final VaccineRepository vaccineRepository;

    @Override
    public Vaccine execute(String id) {
        return vaccineRepository.findById(id)
                .orElseThrow(VaccineNotFoundException::new);
    }
}
