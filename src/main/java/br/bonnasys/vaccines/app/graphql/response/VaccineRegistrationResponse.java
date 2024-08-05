package br.bonnasys.vaccines.app.graphql.response;

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
