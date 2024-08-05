package br.bonnasys.vaccines.app.rest.response;

import java.time.OffsetDateTime;
import java.util.List;

public record HealthCenterDetailedResponse(
        String id,
        String name,
        String address,
        String state,
        String neighborhood,
        List<Stock> stock,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {

    public record Stock(String id, String name, Integer amount) {
    }
}
