package br.bonnasys.vaccines.app.rest.controller;

import br.bonnasys.vaccines.app.graphql.response.PatientResponse;
import br.bonnasys.vaccines.app.mapper.PatientMapper;
import br.bonnasys.vaccines.app.rest.PatientApi;
import br.bonnasys.vaccines.app.rest.request.CreatePatientRequest;
import br.bonnasys.vaccines.app.rest.request.VaccinatePatientRequest;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.PatientFacade;
import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientCommand;
import br.bonnasys.vaccines.domain.usecase.patient.vaccinate.VaccinatePatientCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@AllArgsConstructor
public class PatientRestController implements PatientApi {
    private final PatientFacade patientFacade;
    private final PatientMapper patientMapper;

    @Override
    public ResponseEntity<PatientResponse> get(String id) {
        Patient patient = patientFacade.getPatientByIdUseCase(id);
        PatientResponse response = patientMapper.toPatientResponseWithHistory(patient);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> create(CreatePatientRequest request) {
        CreatePatientCommand command = new CreatePatientCommand(
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getBirthdate()
        );
        Patient patient = patientFacade.createPatientUseCase(command);
        return ResponseEntity.created(
                fromCurrentRequest()
                        .pathSegment(patient.getId())
                        .build().toUri()
        ).build();
    }

    @Override
    public ResponseEntity<Page<PatientResponse>> search(Pageable pageable) {
        Page<Patient> patients = patientFacade.searchPatientUseCase(null, pageable);
        Page<PatientResponse> responses = patients.map(patientMapper::toPatientResponse);
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<PatientResponse> vaccinate(String id, VaccinatePatientRequest request) {
        VaccinatePatientCommand command = new VaccinatePatientCommand(id, request.healthCenterId(), request.vaccineId());
        Patient patient = patientFacade.vaccinatePatientUseCase(command);
        PatientResponse response = patientMapper.toPatientResponse(patient);
        return ResponseEntity.ok(response);
    }
}
