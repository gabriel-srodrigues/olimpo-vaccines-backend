package br.bonnasys.vaccines.domain.usecase;

import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineCommand;
import br.bonnasys.vaccines.domain.usecase.vaccine.create.CreateVaccineUseCase;
import br.bonnasys.vaccines.domain.usecase.vaccine.delete.DeleteVaccineUseCase;
import br.bonnasys.vaccines.domain.usecase.vaccine.retrieve.get.GetVaccineUseCase;
import br.bonnasys.vaccines.domain.usecase.vaccine.retrieve.search.SearchVaccineUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VaccineFacade {
    private final CreateVaccineUseCase createVaccineUseCase;
    private final SearchVaccineUseCase searchVaccineUseCase;
    private final GetVaccineUseCase getVaccineUseCase;
    private final DeleteVaccineUseCase deleteVaccineUseCase;


    public Vaccine createVaccineUseCase(CreateVaccineCommand command) {
        return createVaccineUseCase.execute(command);
    }

    public Page<Vaccine> searchVaccineUseCase(Pageable pageable) {
        return searchVaccineUseCase.execute(pageable);
    }

    public Vaccine getVaccineUseCase(String id) {
        return getVaccineUseCase.execute(id);
    }

    public void deleteVaccineUseCase(String id) {
        deleteVaccineUseCase.execute(id);
    }
}
