package br.bonnasys.vaccines.app.graphql.patients.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PatientResponse {
    private String id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthdate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
