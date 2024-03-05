package br.bonnasys.vaccines.app.rest;

import br.bonnasys.vaccines.app.graphql.response.PatientResponse;
import br.bonnasys.vaccines.app.rest.controller.dto.request.CreatePatientRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Patients")
@RequestMapping("patients")
public interface PatientApi {

    @GetMapping("{id}")
    ResponseEntity<PatientResponse> get(@PathVariable String id);

    @PostMapping
    ResponseEntity<Void> create(@RequestBody CreatePatientRequest request);
}
