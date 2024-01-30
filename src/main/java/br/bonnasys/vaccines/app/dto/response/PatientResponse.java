package br.bonnasys.vaccines.app.dto.response;

import br.bonnasys.vaccines.domain.model.VaccineRegistration;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PatientResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthdate;
    private List<VaccineRegistrationResponse> history;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
