package br.bonnasys.vaccines.domain.usecase.vaccine.create.impl;

import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineCommand;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CreateVaccineUseCaseImpl implements CreateVaccineUseCase {

    @Override
    public Vaccine execute(CreateVaccineCommand command) {
        return null;


@Service
@AllArgsConstructor
public class CreateVaccineUseCaseImpl implements CreateVaccineUseCase {
    private final VaccineRepository vaccineRepository;

    @Override
    public Vaccine execute(CreateVaccineCommand command) {
        Vaccine vaccine = new Vaccine();
        vaccine.setName(command.name());
        vaccine.setProducer(command.producer());
        return vaccineRepository.save(vaccine);
    }
}
