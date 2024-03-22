package br.bonnasys.vaccines.app.rest;

import br.bonnasys.vaccines.app.graphql.response.PatientResponse;
import br.bonnasys.vaccines.app.rest.request.CreatePatientRequest;
import br.bonnasys.vaccines.app.rest.request.VaccinatePatientRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Patients")
@RequestMapping("patients")
public interface PatientApi {

    @GetMapping("{id}")
    ResponseEntity<PatientResponse> get(@PathVariable String id);

    @PostMapping
    ResponseEntity<Void> create(@RequestBody CreatePatientRequest request);

    @GetMapping
    ResponseEntity<Page<PatientResponse>> search(@PageableDefault Pageable pageable);

    @PutMapping("{id}")
    ResponseEntity<PatientResponse> vaccinate(String id, VaccinatePatientRequest request);
}
