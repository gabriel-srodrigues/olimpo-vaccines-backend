package br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.history;

import br.bonnasys.vaccines.domain.model.VaccineRegistration;

import java.util.List;

public interface SearchPatientHistoryUseCase {

    List<VaccineRegistration> execute(String id);
}
