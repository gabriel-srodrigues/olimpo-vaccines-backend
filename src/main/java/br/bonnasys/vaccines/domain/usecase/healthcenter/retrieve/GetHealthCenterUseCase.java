package br.bonnasys.vaccines.domain.usecase.healthcenter.retrieve;

import br.bonnasys.vaccines.domain.model.HealthCenter;

public interface GetHealthCenterUseCase {

    HealthCenter execute(String id);
}
