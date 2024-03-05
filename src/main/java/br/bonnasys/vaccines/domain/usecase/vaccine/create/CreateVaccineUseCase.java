package br.bonnasys.vaccines.domain.usecase.vaccine.create;

import br.bonnasys.vaccines.domain.model.Vaccine;

public interface CreateVaccineUseCase {
    Vaccine execute(CreateVaccineCommand command);
}
