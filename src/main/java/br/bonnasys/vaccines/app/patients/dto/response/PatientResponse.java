package br.bonnasys.vaccines.app.patients.dto.response;

import br.bonnasys.vaccines.domain.model.VaccineRegistration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PatientResponse {

    @Schema(implementation = UUID.class)
    private String id;

    @Schema(example = "Rick Sanches")
    private String name;

    @Schema(example = "(99) 99999-9999")
    private String phone;

    @Schema(example = "rick.sanches@bonnasys.com.br")
    private String email;

    @Schema(example = "1946-02-12")
    private LocalDate birthdate;

    private List<VaccineRegistrationResponse> history;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
