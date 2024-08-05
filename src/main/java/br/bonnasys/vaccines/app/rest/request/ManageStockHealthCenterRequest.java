package br.bonnasys.vaccines.app.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record ManageStockHealthCenterRequest(
        @Schema(implementation = UUID.class)
        String vaccineId,
        @Schema(example = "20")
        Integer amount
) {

}
