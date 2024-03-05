package br.bonnasys.vaccines.domain.usecase.vaccine.create.impl;

import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineCommand;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateVaccineUseCaseImpl implements CreateVaccineUseCase {

    @Override
    public Vaccine execute(CreateVaccineCommand command) {
        return null;
    }
}
