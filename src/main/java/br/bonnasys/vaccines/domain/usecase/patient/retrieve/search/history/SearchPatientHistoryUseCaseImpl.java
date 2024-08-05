package br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.history;

import br.bonnasys.vaccines.domain.exception.PatientNotFoundException;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.model.VaccineRegistration;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchPatientHistoryUseCaseImpl implements SearchPatientHistoryUseCase {
    private final PatientRepository patientRepository;

    @Override
    public List<VaccineRegistration> execute(String id) {
        return patientRepository.findById(id)
                .map(Patient::getHistory)
//                .map(patient -> patient.getHistory())
                .orElseThrow(PatientNotFoundException::new);
    }
}
