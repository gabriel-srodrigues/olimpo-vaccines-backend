package br.bonnasys.vaccines.domain.usecase.healthcenter.retrieve;

import br.bonnasys.vaccines.domain.model.HealthCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchHeathCenterUseCase {

    Page<HealthCenter> execute(Pageable page);
}
