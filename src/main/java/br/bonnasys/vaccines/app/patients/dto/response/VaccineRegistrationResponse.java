package br.bonnasys.vaccines.app.patients.dto.response;

import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.model.Vaccine;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VaccineRegistrationResponse {
    private String id;
    private VaccineResponse vaccine;
    private HealthCenterResponse healthCenter;
    private OffsetDateTime registrationDate;
}
