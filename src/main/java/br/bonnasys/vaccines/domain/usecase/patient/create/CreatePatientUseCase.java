package br.bonnasys.vaccines.domain.usecase.patient.create;

import br.bonnasys.vaccines.domain.model.Patient;

public interface CreatePatientUseCase {

    Patient execute(CreatePatientCommand command);

}
