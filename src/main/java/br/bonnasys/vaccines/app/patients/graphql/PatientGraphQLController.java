package br.bonnasys.vaccines.app.patients.graphql;

import br.bonnasys.vaccines.app.patients.dto.response.PageResponse;
import br.bonnasys.vaccines.app.patients.dto.response.PaginationResponse;
import br.bonnasys.vaccines.app.patients.dto.response.PatientResponse;
import br.bonnasys.vaccines.app.patients.mapper.PatientMapper;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.patient.PatientFacade;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientsCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PatientGraphQLController {
    private final PatientFacade patientFacade;
    private final PatientMapper patientMapper;

    @QueryMapping(name = "searchPatients")
    public PaginationResponse<PatientResponse> searchPatients(@Argument Integer page,
                                             @Argument Integer size,
                                             @Argument String term) {
        SearchPatientsCommand command = new SearchPatientsCommand(term, PageRequest.of(page, size));
        Page<Patient> patientPage = patientFacade.searchPatientsUseCase(command);
        Page<PatientResponse> patientResponse = patientPage.map(patientMapper::toPatientResponseWithoutHistory);
        PageResponse pageResponse = new PageResponse(
                patientPage.getNumber(),
                patientPage.getSize(),
                patientPage.getTotalElements(),
                patientPage.getTotalPages());
        return new PaginationResponse<>(patientResponse.getContent(), pageResponse);
    }

    @QueryMapping(name = "countPatients")
    public Long countPatients(@Argument String term) {
        return patientFacade.countPatientsUseCase(term);
    }

    @QueryMapping(name = "getPatientById")
    public PatientResponse getPatientById(@Argument String id) {
        Patient patient = patientFacade.getPatientByIdUseCase(id);
        return patientMapper.toPatientResponse(patient);
    }
}
