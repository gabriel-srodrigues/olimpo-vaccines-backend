package br.bonnasys.vaccines.domain.usecase.patient;

import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientCommand;
import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.delete.DeletePatientUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.count.CountPatientsUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.get.GetPatientByIdUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientsCommand;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientsUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.update.UpdatePatientUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class PatientFacade {
    private final CreatePatientUseCase createPatientUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;
    private final CountPatientsUseCase countPatientsUseCase;
    private final SearchPatientsUseCase searchPatientsUseCase;
    private final GetPatientByIdUseCase getPatientByIdUseCase;
    private final DeletePatientUseCase deletePatientUseCase;

    public Patient createPatientUseCase(CreatePatientCommand command) {
        return createPatientUseCase.execute(command);
    }

    public Patient updatePatientUseCase(String id, Map<String, Object> fields) {
        return updatePatientUseCase.execute(id, fields);
    }

    public Long countPatientsUseCase(String term) {
        return countPatientsUseCase.execute(term);
    }

    public Page<Patient> searchPatientsUseCase(SearchPatientsCommand command) {
        return searchPatientsUseCase.execute(command);
    }

    public Patient getPatientByIdUseCase(String id) {
        return getPatientByIdUseCase.execute(id);
    }

    public void deletePatientUseCase(String id) {
        deletePatientUseCase.execute(id);
    }
}
