package br.bonnasys.vaccines.domain.usecase.vaccine.retrieve.get;

import br.bonnasys.vaccines.domain.model.Vaccine;

public interface GetVaccineUseCase {
    Vaccine execute(String id);
}
