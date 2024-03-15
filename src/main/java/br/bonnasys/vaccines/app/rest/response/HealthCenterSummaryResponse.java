package br.bonnasys.vaccines.app.rest.response;

public record HealthCenterSummaryResponse(
        String id,
        String name,
        String address,
        String state,
        String neighborhood
) {
}
