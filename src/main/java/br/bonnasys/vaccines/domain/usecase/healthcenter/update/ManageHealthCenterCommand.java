package br.bonnasys.vaccines.domain.usecase.healthcenter.update;

public record ManageHealthCenterCommand(String vaccineId, String healthCenterId, Integer amount) {
}
