package br.bonnasys.vaccines.domain.usecase.patient.vaccinate;

import br.bonnasys.vaccines.domain.model.Patient;

public interface VaccinatePatientUseCase {
    Patient execute(VaccinatePatientCommand command);
}
