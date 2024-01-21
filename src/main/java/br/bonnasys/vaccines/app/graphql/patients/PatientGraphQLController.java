package br.bonnasys.vaccines.app.graphql.patients;

import br.bonnasys.vaccines.app.graphql.patients.response.PageResponse;
import br.bonnasys.vaccines.app.graphql.patients.response.PaginationResponse;
import br.bonnasys.vaccines.app.graphql.patients.response.PatientResponse;
import br.bonnasys.vaccines.app.mapper.PatientMapper;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.count.CountPatientsUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientsCommand;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PatientGraphQLController {
    private final SearchPatientsUseCase searchPatientsUseCase;
    private final CountPatientsUseCase countPatientsUseCase;
    private final PatientMapper patientMapper;

    @QueryMapping(name = "searchPatients")
    public PaginationResponse<PatientResponse> searchPatients(@Argument Integer page,
                                             @Argument Integer size,
                                             @Argument String term) {
        SearchPatientsCommand command = new SearchPatientsCommand(term, PageRequest.of(page, size));
        Page<Patient> patientPage = searchPatientsUseCase.execute(command);
        Page<PatientResponse> patientResponse = patientPage.map(patientMapper::toPatientResponse);
        PageResponse pageResponse = new PageResponse(
                patientPage.getNumber(),
                patientPage.getSize(),
                patientPage.getTotalElements(),
                patientPage.getTotalPages());
        return new PaginationResponse<>(patientResponse.getContent(), pageResponse);
    }

    @QueryMapping(name = "countPatients")
    public Long countPatients(@Argument String term) {
        return countPatientsUseCase.execute(term);
    }
}
