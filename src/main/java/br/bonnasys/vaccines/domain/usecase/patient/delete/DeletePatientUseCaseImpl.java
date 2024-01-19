package br.bonnasys.vaccines.domain.usecase.patient.delete;

import br.bonnasys.vaccines.domain.exception.PatientNotFoundException;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePatientUseCaseImpl implements DeletePatientUseCase {
    private final PatientRepository patientRepository;

    @Override
    public void execute(String id) {
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        patientRepository.delete(patient);
    }
}
