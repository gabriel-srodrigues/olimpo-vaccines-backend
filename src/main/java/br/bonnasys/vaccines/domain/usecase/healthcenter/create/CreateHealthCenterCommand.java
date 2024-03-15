package br.bonnasys.vaccines.domain.usecase.healthcenter.create;

public record CreateHealthCenterCommand(
        String name,
        String address,
        String state,
        String neighborhood
) {
}
