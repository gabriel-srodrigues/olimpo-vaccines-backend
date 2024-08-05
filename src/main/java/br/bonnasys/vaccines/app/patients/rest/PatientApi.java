package br.bonnasys.vaccines.app.patients.rest;

import br.bonnasys.vaccines.app.patients.dto.request.CreatePatientRequest;
import br.bonnasys.vaccines.app.patients.dto.response.PatientResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@Tag(name = "Patients")
@RequestMapping("patients")
public interface PatientApi {

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(operationId = "getPatientById",
            description = "endpoint to retrieve patient by Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(schema = @Schema(implementation = PatientResponse.class))),
                    @ApiResponse(responseCode = "404",
                            description = "NOT_FOUND",
                            content = @Content(schema = @Schema(implementation = Object.class))),
            })
    ResponseEntity<PatientResponse> get(@PathVariable String id);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Operation(operationId = "createPatient",
            description = "endpoint to express create patient",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "CREATED",
                            headers = @Header(name = "Location", schema = @Schema(example = "950ee5c9-536a-439a-8517-a3987b2cddb6")),
                            content = @Content(schema = @Schema(implementation = Object.class)))
            })
    ResponseEntity<Void> create(@RequestBody @Valid CreatePatientRequest createPatientRequest);


    @PatchMapping(value = "{id}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @Operation(operationId = "updatePatient",
            description = "endpoint to update patient",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(schema = @Schema(implementation = Object.class))),
                    @ApiResponse(responseCode = "404",
                            description = "NOT_FOUND",
                            content = @Content(schema = @Schema(implementation = Object.class)))
            })
    ResponseEntity<PatientResponse> update(@PathVariable String id,
                                           @RequestBody
                                           @Schema(implementation = CreatePatientRequest.class)
                                           Map<String, Object> fields);

    @DeleteMapping(value = "{id}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @Operation(operationId = "deletePatientById",
            description = "endpoint to delete patient by id",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "NO_CONTENT",
                            content = @Content),
                    @ApiResponse(responseCode = "404",
                            description = "NOT_FOUND",
                            content = @Content(schema = @Schema(implementation = Object.class)))
            })
    ResponseEntity<Void> delete(@PathVariable String id);

}
