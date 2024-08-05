package br.bonnasys.vaccines.app.patients.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreatePatientRequest {

    @Schema(example = "Gabriel Rodrigues")
    private String name;

    @Schema(example = "gabriel.rodrigues@bonnasys.com.br")
    private String email;

    @Schema(example = "(99) 99999-9999")
    private String phone;

    @Schema(example = "1999-07-08")
    private LocalDate birthdate;

}
