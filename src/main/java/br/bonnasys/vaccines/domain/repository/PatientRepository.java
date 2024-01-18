package br.bonnasys.vaccines.domain.repository;

import br.bonnasys.vaccines.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}
