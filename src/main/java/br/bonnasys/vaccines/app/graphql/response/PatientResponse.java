package br.bonnasys.vaccines.app.graphql.response;

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
