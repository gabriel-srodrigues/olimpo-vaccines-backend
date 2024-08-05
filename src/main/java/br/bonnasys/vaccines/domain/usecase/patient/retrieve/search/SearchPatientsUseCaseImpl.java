package br.bonnasys.vaccines.domain.usecase.patient.retrieve.search;

import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchPatientsUseCaseImpl implements SearchPatientsUseCase {
    private final PatientRepository patientRepository;

    @Override
    public Page<Patient> execute(SearchPatientsCommand command) {
        return patientRepository
                .findAll((root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (command.name() != null) {
                        String term = "%" + command.name() + "%";
                        Predicate nameFilter = criteriaBuilder.like(root.get("name"), term);
                        predicates.add(nameFilter);
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }, command.page());
    }
}
