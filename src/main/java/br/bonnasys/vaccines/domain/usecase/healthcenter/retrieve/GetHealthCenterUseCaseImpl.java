package br.bonnasys.vaccines.domain.usecase.healthcenter.retrieve;

import br.bonnasys.vaccines.domain.exception.HealthCenterNotFoundException;
import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.repository.HealthCenterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetHealthCenterUseCaseImpl implements GetHealthCenterUseCase {
    private final HealthCenterRepository healthCenterRepository;
    @Override
    public HealthCenter execute(String id) {
        return healthCenterRepository.findById(id).orElseThrow(HealthCenterNotFoundException::new);
    }
}
