package br.bonnasys.vaccines.domain.usecase.patient.retrieve.search;

import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchPatientUseCaseImpl implements SearchPatientUseCase{
    private final PatientRepository patientRepository;

    @Override
    public Page<Patient> execute(SearchPatientCommand command, Pageable page) {
        return patientRepository.findAll(filters(command), page);
    }

    private Specification<Patient> filters(SearchPatientCommand command) {
        if (command == null) {
            return null;
        }
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (command.name() != null && !command.name().isBlank()) {
                //name like '%Pedro%'
                String term = "%" + command.name() + "%";
                Predicate nameFilter = criteriaBuilder.like(root.get("name"), term);
                predicates.add(nameFilter);
            }

            if (command.email() != null && !command.email().isBlank()) {
                //email like '%Pedro%'
                String term = "%" + command.email() + "%";
                Predicate emailFilter = criteriaBuilder.like(root.get("email"), term);
                predicates.add(emailFilter);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
