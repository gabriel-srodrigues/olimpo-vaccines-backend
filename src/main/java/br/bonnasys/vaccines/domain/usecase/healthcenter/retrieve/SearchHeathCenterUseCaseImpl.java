package br.bonnasys.vaccines.domain.usecase.healthcenter.retrieve;

import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.repository.HealthCenterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchHeathCenterUseCaseImpl implements SearchHeathCenterUseCase {
    private final HealthCenterRepository healthCenterRepository;

    @Override
    public Page<HealthCenter> execute(Pageable page) {
        return healthCenterRepository.findAll(page);
    }
}
