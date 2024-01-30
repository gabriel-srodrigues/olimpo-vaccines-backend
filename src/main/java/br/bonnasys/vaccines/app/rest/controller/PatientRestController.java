package br.bonnasys.vaccines.app.rest.controller;

import br.bonnasys.vaccines.app.dto.response.PatientResponse;
import br.bonnasys.vaccines.app.mapper.PatientMapper;
import br.bonnasys.vaccines.app.rest.PatientApi;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.get.GetPatientByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PatientRestController implements PatientApi {
    private final GetPatientByIdUseCase getPatientByIdUseCase;
    private final PatientMapper patientMapper;

    @Override
    public ResponseEntity<PatientResponse> get(String id) {
        Patient patient = getPatientByIdUseCase.execute(id);
        PatientResponse response = patientMapper.toPatientResponse(patient);
        return ResponseEntity.ok(response);
    }
}
