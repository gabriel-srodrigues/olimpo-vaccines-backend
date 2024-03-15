package br.bonnasys.vaccines.domain.usecase.healthcenter.create;

import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.repository.HealthCenterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateHealthCenterUseCaseImpl implements CreateHealthCenterUseCase {
    private final HealthCenterRepository healthCenterRepository;

    @Override
    public HealthCenter execute(CreateHealthCenterCommand command) {
        HealthCenter healthCenter = new HealthCenter();
        healthCenter.setName(command.name());
        healthCenter.setState(command.state());
        healthCenter.setAddress(command.address());
        healthCenter.setNeighborhood(command.neighborhood());
        return healthCenterRepository.save(healthCenter);
    }
}
