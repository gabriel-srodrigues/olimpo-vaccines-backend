package br.bonnasys.vaccines.app.graphql.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class HealthCenterResponse {
    private String id;
    private String name;
    private String address;
    private String state;
    private String neighborhood;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;


}
