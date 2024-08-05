package br.bonnasys.vaccines.app.rest.controller;

import br.bonnasys.vaccines.app.mapper.HealthCenterMapper;
import br.bonnasys.vaccines.app.rest.HealthCenterApi;
import br.bonnasys.vaccines.app.rest.request.CreateHealthCenterRequest;
import br.bonnasys.vaccines.app.rest.request.ManageStockHealthCenterRequest;
import br.bonnasys.vaccines.app.rest.response.HealthCenterDetailedResponse;
import br.bonnasys.vaccines.app.rest.response.HealthCenterSummaryResponse;
import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.usecase.HealthCenterFacade;
import br.bonnasys.vaccines.domain.usecase.healthcenter.create.CreateHealthCenterCommand;
import br.bonnasys.vaccines.domain.usecase.healthcenter.update.ManageHealthCenterCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class HealthCenterController implements HealthCenterApi {
    private final HealthCenterFacade healthCenterFacade;
    private final HealthCenterMapper healthCenterMapper;

    @Override
    public ResponseEntity<Void> create(CreateHealthCenterRequest request) {
        CreateHealthCenterCommand createHealthCenterCommand = healthCenterMapper.requestToCommand(request);
        HealthCenter healthCenter = healthCenterFacade.createHealthCenterUseCase(createHealthCenterCommand);
        return ResponseEntity.created(URI.create("/heath-centers/" + healthCenter.getId())).build();
    }

    @Override
    public ResponseEntity<Page<HealthCenterSummaryResponse>> search(Pageable page) {
        Page<HealthCenter> healthCenters = healthCenterFacade.searchHeathCenterUseCase(page);
        Page<HealthCenterSummaryResponse> response = healthCenters.map(healthCenterMapper::toSummaryResponse);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<HealthCenterDetailedResponse> get(String id) {
        HealthCenter healthCenter = healthCenterFacade.getHealthCenterUseCase(id);
        HealthCenterDetailedResponse response = healthCenterMapper.toDetailedResponse(healthCenter);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<HealthCenterDetailedResponse> insertToStock(String id, ManageStockHealthCenterRequest request) {
        ManageHealthCenterCommand command = healthCenterMapper.manageStockRequestToCommand(id, request);
        HealthCenter healthCenter = healthCenterFacade.insertToStockHealthCenterUseCase(command);
        HealthCenterDetailedResponse response = healthCenterMapper.toDetailedResponse(healthCenter);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<HealthCenterDetailedResponse> removeOfStock(String id, ManageStockHealthCenterRequest request) {
        ManageHealthCenterCommand command = healthCenterMapper.manageStockRequestToCommand(id, request);
        HealthCenter healthCenter = healthCenterFacade.removeOfStockHealthCenterUseCase(command);
        HealthCenterDetailedResponse response = healthCenterMapper.toDetailedResponse(healthCenter);
        return ResponseEntity.ok(response);
    }
}
