package br.bonnasys.vaccines.domain.usecase.patient.create;

import br.bonnasys.vaccines.domain.model.Patient;

public sealed interface CreatePatientUseCase permits CreatePatientUseCaseImpl {

    Patient execute(CreatePatientCommand command);

}
