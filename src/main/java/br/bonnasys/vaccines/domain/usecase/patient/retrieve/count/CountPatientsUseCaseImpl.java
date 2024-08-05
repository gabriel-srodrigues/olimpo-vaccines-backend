package br.bonnasys.vaccines.domain.usecase.patient.retrieve.count;

import br.bonnasys.vaccines.domain.repository.PatientRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CountPatientsUseCaseImpl implements CountPatientsUseCase {
    private final PatientRepository patientRepository;

    @Override
    public Long execute(String name) {
        return patientRepository.count(((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                String term = "%" + name + "%";
                Predicate nameFilter = criteriaBuilder.like(root.get("name"), term);
                predicates.add(nameFilter);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
