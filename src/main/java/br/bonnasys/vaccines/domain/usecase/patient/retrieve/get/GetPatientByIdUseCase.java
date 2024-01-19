package br.bonnasys.vaccines.domain.usecase.patient.retrieve.get;

import br.bonnasys.vaccines.domain.model.Patient;

public interface GetPatientByIdUseCase {
    Patient execute(String id);

}
