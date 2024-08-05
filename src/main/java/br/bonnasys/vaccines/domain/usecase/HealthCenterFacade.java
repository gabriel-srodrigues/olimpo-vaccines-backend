package br.bonnasys.vaccines.domain.usecase;

import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.usecase.healthcenter.create.CreateHealthCenterCommand;
import br.bonnasys.vaccines.domain.usecase.healthcenter.create.CreateHealthCenterUseCase;
import br.bonnasys.vaccines.domain.usecase.healthcenter.retrieve.GetHealthCenterUseCase;
import br.bonnasys.vaccines.domain.usecase.healthcenter.retrieve.SearchHeathCenterUseCase;
import br.bonnasys.vaccines.domain.usecase.healthcenter.update.InsertToStockHealthCenterUseCase;
import br.bonnasys.vaccines.domain.usecase.healthcenter.update.ManageHealthCenterCommand;
import br.bonnasys.vaccines.domain.usecase.healthcenter.update.RemoveOfStockHealthCenterUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HealthCenterFacade {
    private final CreateHealthCenterUseCase createHealthCenterUseCase;
    private final SearchHeathCenterUseCase searchHeathCenterUseCase;
    private final GetHealthCenterUseCase getHealthCenterUseCase;
    private final InsertToStockHealthCenterUseCase insertToStockHealthCenterUseCase;
    private final RemoveOfStockHealthCenterUseCase removeOfStockHealthCenterUseCase;

    public Page<HealthCenter> searchHeathCenterUseCase(Pageable pageable) {
        return this.searchHeathCenterUseCase.execute(pageable);
    }

    public HealthCenter getHealthCenterUseCase(String id) {
        return this.getHealthCenterUseCase.execute(id);
    }

    public HealthCenter createHealthCenterUseCase(CreateHealthCenterCommand command) {
        return this.createHealthCenterUseCase.execute(command);
    }

    public HealthCenter insertToStockHealthCenterUseCase(ManageHealthCenterCommand command) {
        return insertToStockHealthCenterUseCase.execute(command);
    }

    public HealthCenter removeOfStockHealthCenterUseCase(ManageHealthCenterCommand command) {
        return removeOfStockHealthCenterUseCase.execute(command);
    }
}
