package br.bonnasys.vaccines.app.graphql;

import br.bonnasys.vaccines.app.graphql.response.PageResponse;
import br.bonnasys.vaccines.app.graphql.response.PaginationResponse;
import br.bonnasys.vaccines.app.graphql.response.PatientResponse;
import br.bonnasys.vaccines.app.graphql.response.VaccineRegistrationResponse;
import br.bonnasys.vaccines.app.mapper.PatientMapper;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.PatientFacade;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientGraphQLController {
    private final PatientFacade patientFacade;
    private final PatientMapper mapper;

    @QueryMapping
    public PaginationResponse<PatientResponse> searchPatients(
            @Argument Integer page,
            @Argument Integer size,
            @Argument String name,
            @Argument String email) {

        SearchPatientCommand command = new SearchPatientCommand(name, email);
        Pageable pageable = PageRequest.of(page, size);

        Page<Patient> patientPage = patientFacade.searchPatientUseCase(command, pageable);

        Page<PatientResponse> patientResponse = patientPage.map(mapper::toPatientResponse);

        PageResponse pageResponse = new PageResponse(patientResponse.getNumber(),
                patientResponse.getSize(),
                patientResponse.getTotalElements(),
                patientResponse.getTotalPages());
        return new PaginationResponse<>(patientResponse.getContent(), pageResponse);
    }

    @QueryMapping("getPatientById")
    public PatientResponse get(@Argument String id) {
        Patient patient = patientFacade.getPatientByIdUseCase(id);
        return mapper.toPatientResponse(patient);
    }

    @SchemaMapping(value = "history", typeName = "PatientDetailed")
    public List<VaccineRegistrationResponse> getHistory(PatientResponse response) {
        return patientFacade.searchPatientHistoryUseCase(response.getId()).stream()
                .map(mapper::toVaccineRegistrationResponse).toList();
    }
}
