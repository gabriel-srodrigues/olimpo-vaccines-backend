package br.bonnasys.vaccines.domain.usecase.healthcenter.create;

import br.bonnasys.vaccines.domain.model.HealthCenter;

public interface CreateHealthCenterUseCase {

    HealthCenter execute(CreateHealthCenterCommand command);
}
