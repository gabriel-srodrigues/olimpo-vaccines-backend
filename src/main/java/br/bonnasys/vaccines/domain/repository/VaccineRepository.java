package br.bonnasys.vaccines.domain.repository;

import br.bonnasys.vaccines.domain.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, String> {
}
