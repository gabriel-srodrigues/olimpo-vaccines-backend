package br.bonnasys.vaccines.domain.usecase;

import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.model.VaccineRegistration;
import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientCommand;
import br.bonnasys.vaccines.domain.usecase.patient.create.CreatePatientUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.get.GetPatientByIdUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientCommand;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.history.SearchPatientHistoryUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.vaccinate.VaccinatePatientUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.vaccinate.VaccinatePatientCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PatientFacade {
    private final GetPatientByIdUseCase getPatientByIdUseCase;
    private final CreatePatientUseCase createPatientUseCase;
    private final SearchPatientUseCase searchPatientUseCase;
    private final VaccinatePatientUseCase vaccinatePatientUseCase;
    private final SearchPatientHistoryUseCase searchPatientHistoryUseCase;

    public Patient getPatientByIdUseCase(String id) {
        return getPatientByIdUseCase.execute(id);
    }

    public Patient createPatientUseCase(CreatePatientCommand command) {
        return createPatientUseCase.execute(command);
    }

    public Page<Patient> searchPatientUseCase(SearchPatientCommand command, Pageable pageable) {
        return searchPatientUseCase.execute(command, pageable);
    }

    public Patient vaccinatePatientUseCase(VaccinatePatientCommand command) {
        return vaccinatePatientUseCase.execute(command);
    }

    public List<VaccineRegistration> searchPatientHistoryUseCase(String id) {
        return searchPatientHistoryUseCase.execute(id);
    }
}
