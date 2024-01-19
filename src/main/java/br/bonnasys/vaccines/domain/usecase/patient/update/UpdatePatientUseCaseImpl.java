package br.bonnasys.vaccines.domain.usecase.patient.update;

import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;
import static org.springframework.util.ReflectionUtils.setField;

@Slf4j
@Service
@AllArgsConstructor
public class UpdatePatientUseCaseImpl implements UpdatePatientUseCase {
    private final PatientRepository patientRepository;
    private final ObjectMapper mapper;

    @Override
    public Patient execute(String id, Map<String, Object> fields) {
        Patient retrievedPatient = patientRepository.findById(id).orElseThrow();
        Patient request = mapper.convertValue(fields, Patient.class);

        fields.forEach((property, value) -> {
            Field field = findField(Patient.class, property);
            if (field == null) {
                log.error("field '%s' not found in entity, I will skip it");
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, request);
            setField(field, retrievedPatient, newValue);
        });

        return patientRepository.save(retrievedPatient);
    }

}
