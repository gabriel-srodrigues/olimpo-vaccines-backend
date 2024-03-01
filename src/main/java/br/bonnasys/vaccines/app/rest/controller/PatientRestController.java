package br.bonnasys.vaccines.app.rest.controller;

import br.bonnasys.vaccines.app.dto.response.PatientResponse;
import br.bonnasys.vaccines.app.mapper.PatientMapper;
import br.bonnasys.vaccines.app.rest.PatientApi;
import br.bonnasys.vaccines.app.rest.controller.dto.request.CreatePatientRequest;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientCommand;
import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.get.GetPatientByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@AllArgsConstructor
public class PatientRestController implements PatientApi {
    private final GetPatientByIdUseCase getPatientByIdUseCase;
    private final CreatePatientUseCase createPatientUseCase;
    private final PatientMapper patientMapper;

    @Override
    public ResponseEntity<PatientResponse> get(String id) {
        Patient patient = getPatientByIdUseCase.execute(id);
        PatientResponse response = patientMapper.toPatientResponse(patient);
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
        Patient patient = createPatientUseCase.execute(command);
        return ResponseEntity.created(
                fromCurrentRequest()
                        .pathSegment(patient.getId())
                        .build().toUri()
        ).build();
    }
}
