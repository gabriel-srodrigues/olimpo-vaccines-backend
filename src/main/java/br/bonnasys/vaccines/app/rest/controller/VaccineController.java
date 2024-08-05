package br.bonnasys.vaccines.app.rest.controller;

import br.bonnasys.vaccines.app.mapper.VaccineMapper;
import br.bonnasys.vaccines.app.rest.VaccineApi;
import br.bonnasys.vaccines.app.rest.request.CreateVaccineRequest;
import br.bonnasys.vaccines.app.rest.response.VaccineResponse;
import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.usecase.VaccineFacade;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@AllArgsConstructor
public class VaccineController implements VaccineApi {
    private final VaccineFacade vaccineFacade;
    private final VaccineMapper vaccineMapper;

    @Override
    public ResponseEntity<Void> create(CreateVaccineRequest request) {
        CreateVaccineCommand command = new CreateVaccineCommand(request.name(), request.producer());
        Vaccine actualVaccine = vaccineFacade.createVaccineUseCase(command);
        return ResponseEntity.created(fromCurrentRequest().pathSegment(actualVaccine.getId()).build().toUri()).build();
    }

    @Override
    public ResponseEntity<Page<VaccineResponse>> search(Pageable pageable) {
        Page<Vaccine> vaccines = vaccineFacade.searchVaccineUseCase(pageable);
        Page<VaccineResponse> response = vaccines.map(vaccineMapper::domainObjectToResponse);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<VaccineResponse> getById(String id) {
        Vaccine actualVaccine = vaccineFacade.getVaccineUseCase(id);
        VaccineResponse response = vaccineMapper.domainObjectToResponse(actualVaccine);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        vaccineFacade.deleteVaccineUseCase(id);
        return ResponseEntity.noContent().build();
    }
}
