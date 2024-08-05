package br.bonnasys.vaccines.domain.usecase.healthcenter.update;

import br.bonnasys.vaccines.domain.exception.HealthCenterNotFoundException;
import br.bonnasys.vaccines.domain.exception.VaccineNotFoundException;
import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.repository.HealthCenterRepository;
import br.bonnasys.vaccines.domain.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsertToStockHealthCenterUseCaseImpl implements InsertToStockHealthCenterUseCase {
    private final HealthCenterRepository healthCenterRepository;
    private final VaccineRepository vaccineRepository;

    @Override
    public HealthCenter execute(ManageHealthCenterCommand command) {
        HealthCenter healthCenter = healthCenterRepository.findById(command.healthCenterId()).orElseThrow(HealthCenterNotFoundException::new);
        Vaccine vaccine = vaccineRepository.findById(command.vaccineId()).orElseThrow(VaccineNotFoundException::new);

        boolean hasVaccineInStock = healthCenter.getStock().get(vaccine) != null;
        if (hasVaccineInStock) {
            Integer actualAmount = healthCenter.getStock().get(vaccine);
            actualAmount += command.amount();
            healthCenter.getStock().put(vaccine, actualAmount);
        } else {
            healthCenter.getStock().put(vaccine, command.amount());
        }

        return healthCenterRepository.save(healthCenter);
    }
}
