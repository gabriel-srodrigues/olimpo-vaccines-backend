package br.bonnasys.vaccines.app.rest;

import br.bonnasys.vaccines.app.dto.response.PatientResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Patients")
@RequestMapping("patients")
public interface PatientApi {

    @GetMapping("{id}")
    ResponseEntity<PatientResponse> get(@PathVariable String id);
}
