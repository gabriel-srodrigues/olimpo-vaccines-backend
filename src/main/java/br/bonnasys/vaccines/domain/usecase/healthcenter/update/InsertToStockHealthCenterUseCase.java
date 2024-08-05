package br.bonnasys.vaccines.domain.usecase.healthcenter.update;

import br.bonnasys.vaccines.domain.model.HealthCenter;

public interface InsertToStockHealthCenterUseCase {

    HealthCenter execute(ManageHealthCenterCommand command);
}
