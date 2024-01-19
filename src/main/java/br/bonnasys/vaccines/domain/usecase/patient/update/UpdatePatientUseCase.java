package br.bonnasys.vaccines.domain.usecase.patient.update;

import br.bonnasys.vaccines.domain.model.Patient;

import java.util.Map;

public interface UpdatePatientUseCase {

    Patient execute(String id, Map<String, Object> fields);

}
