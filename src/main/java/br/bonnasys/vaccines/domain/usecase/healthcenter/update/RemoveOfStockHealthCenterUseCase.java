package br.bonnasys.vaccines.domain.usecase.healthcenter.update;

import br.bonnasys.vaccines.domain.model.HealthCenter;

public interface RemoveOfStockHealthCenterUseCase {
    HealthCenter execute(ManageHealthCenterCommand command);

}
