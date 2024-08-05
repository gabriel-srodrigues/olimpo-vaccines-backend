package br.bonnasys.vaccines.domain.usecase.patient.retrieve.search;

import org.springframework.data.domain.Pageable;

public record SearchPatientsCommand(String name, Pageable page) {
}
