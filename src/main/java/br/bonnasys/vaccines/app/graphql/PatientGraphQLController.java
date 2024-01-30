package br.bonnasys.vaccines.app.graphql;

import br.bonnasys.vaccines.app.dto.response.PageResponse;
import br.bonnasys.vaccines.app.dto.response.PaginationResponse;
import br.bonnasys.vaccines.app.dto.response.PatientResponse;
import br.bonnasys.vaccines.app.mapper.PatientMapper;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.get.GetPatientByIdUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientCommand;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientUseCase;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PatientGraphQLController {
    private final SearchPatientUseCase searchPatientUseCase;
    private final GetPatientByIdUseCase getPatientByIdUseCase;
    private final PatientMapper mapper;

    @QueryMapping
    public PaginationResponse<PatientResponse> searchPatients(
            @Argument Integer page,
            @Argument Integer size,
            @Argument String name,
            @Argument String email) {

        SearchPatientCommand command = new SearchPatientCommand(name, email);
        Pageable pageable = PageRequest.of(page, size);


        Page<Patient> patientPage = searchPatientUseCase.execute(command, pageable);

        Page<PatientResponse> patientResponse = patientPage.map(mapper::toPatientResponse);

        PageResponse pageResponse = new PageResponse(patientResponse.getNumber(),
                patientResponse.getSize(),
                patientResponse.getTotalElements(),
                patientResponse.getTotalPages());
        return new PaginationResponse<>(patientResponse.getContent(), pageResponse);
    }

    @QueryMapping("getPatientById")
    public PatientResponse get(@Argument String id) {
        Patient patient = getPatientByIdUseCase.execute(id);
        return mapper.toPatientResponse(patient);
    }
}
