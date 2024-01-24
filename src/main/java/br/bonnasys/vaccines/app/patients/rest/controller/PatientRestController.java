package br.bonnasys.vaccines.app.patients.rest.controller;

import br.bonnasys.vaccines.app.patients.dto.response.PatientResponse;
import br.bonnasys.vaccines.app.patients.mapper.PatientMapper;
import br.bonnasys.vaccines.app.patients.rest.PatientApi;
import br.bonnasys.vaccines.app.patients.dto.request.CreatePatientRequest;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.patient.PatientFacade;
import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientCommand;
import lombok.AllArgsConstructor;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@AllArgsConstructor
public class PatientRestController implements PatientApi {
    private final PatientFacade patientFacade;
    private final PatientMapper patientMapper;

    @Override
    public ResponseEntity<PatientResponse> get(String id) {
        Patient patient = patientFacade.getPatientByIdUseCase(id);
        PatientResponse response = patientMapper.toPatientResponse(patient);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> create(CreatePatientRequest request) {
        CreatePatientCommand command = new CreatePatientCommand(
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getBirthdate());
        Patient patient = patientFacade.createPatientUseCase(command);
        return ResponseEntity.created(fromCurrentRequest()
                        .path("/{id}").
                        buildAndExpand(patient.getId())
                        .toUri())
                .build();
    }

    @Override
    public ResponseEntity<PatientResponse> update(String id, Map<String, Object> fields) {
        Patient patient = patientFacade.updatePatientUseCase(id, fields);
        PatientResponse response = patientMapper.toPatientResponse(patient);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        patientFacade.deletePatientUseCase(id);
        return ResponseEntity.noContent().build();
    }
}
