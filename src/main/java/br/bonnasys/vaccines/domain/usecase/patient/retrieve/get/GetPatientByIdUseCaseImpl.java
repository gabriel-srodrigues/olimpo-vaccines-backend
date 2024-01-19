package br.bonnasys.vaccines.domain.usecase.patient.retrieve.get;

import br.bonnasys.vaccines.domain.exception.PatientNotFoundException;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPatientByIdUseCaseImpl implements GetPatientByIdUseCase{
    private final PatientRepository repository;

    @Override
    public Patient execute(String id) {
        return repository.findById(id).orElseThrow(PatientNotFoundException::new);
    }
}
