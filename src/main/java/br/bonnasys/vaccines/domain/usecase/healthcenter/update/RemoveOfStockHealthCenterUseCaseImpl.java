package br.bonnasys.vaccines.domain.usecase.healthcenter.update;

import br.bonnasys.vaccines.domain.exception.HealthCenterNotFoundException;
import br.bonnasys.vaccines.domain.exception.StockInsufficientException;
import br.bonnasys.vaccines.domain.exception.VaccineNotFoundException;
import br.bonnasys.vaccines.domain.exception.VaccineNotFoundInStockException;
import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.repository.HealthCenterRepository;
import br.bonnasys.vaccines.domain.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveOfStockHealthCenterUseCaseImpl implements RemoveOfStockHealthCenterUseCase {
    private final HealthCenterRepository healthCenterRepository;
    private final VaccineRepository vaccineRepository;

    @Override
    public HealthCenter execute(ManageHealthCenterCommand command) {
        HealthCenter healthCenter = healthCenterRepository
                .findById(command.healthCenterId())
                .orElseThrow(HealthCenterNotFoundException::new);

        Vaccine vaccine = vaccineRepository
                .findById(command.vaccineId())
                .orElseThrow(VaccineNotFoundException::new);

        //Caso a vacina não exista no centro de saude, retornar um erro
        boolean hasVaccineInStock = healthCenter.getStock().get(vaccine) != null;
        if (!hasVaccineInStock) {
            throw new VaccineNotFoundInStockException(command.healthCenterId(), command.vaccineId());
        }

        //Caso a vacina menos a quantidade que gostaria de remover seja negativa, também iremos retornar um erro
        boolean hasNecessaryAmount = healthCenter.getStock().get(vaccine) - command.amount() > 0;
        if (!hasNecessaryAmount) {
            throw new StockInsufficientException(command.amount(), healthCenter.getStock().get(vaccine));
        }

        Integer actualAmount = healthCenter.getStock().get(vaccine);
        actualAmount -= command.amount();
        healthCenter.getStock().put(vaccine, actualAmount);

        return healthCenterRepository.save(healthCenter);
    }
}
