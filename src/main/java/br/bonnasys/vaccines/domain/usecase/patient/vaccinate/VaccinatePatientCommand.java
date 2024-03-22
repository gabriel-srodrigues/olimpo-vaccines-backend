package br.bonnasys.vaccines.domain.usecase.patient.vaccinate;

public record VaccinatePatientCommand(String patientId, String healthCenterId, String vaccineId) {
}
