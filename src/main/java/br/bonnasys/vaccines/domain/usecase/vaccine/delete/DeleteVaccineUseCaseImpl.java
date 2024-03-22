package br.bonnasys.vaccines.domain.usecase.vaccine.delete;

import br.bonnasys.vaccines.domain.exception.VaccineNotFoundException;
import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteVaccineUseCaseImpl implements DeleteVaccineUseCase {
    private final VaccineRepository vaccineRepository;

    @Override
    public void execute(String id) {
        Vaccine vaccine = vaccineRepository.findById(id).orElseThrow(VaccineNotFoundException::new);
        vaccineRepository.delete(vaccine);
    }
}
