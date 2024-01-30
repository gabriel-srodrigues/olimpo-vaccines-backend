package br.bonnasys.vaccines.app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VaccineResponse {
    private String id;
    private String name;
    private String producer;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;


}
