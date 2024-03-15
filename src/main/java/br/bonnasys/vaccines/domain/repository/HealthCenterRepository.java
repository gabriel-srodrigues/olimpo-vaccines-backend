package br.bonnasys.vaccines.domain.repository;

import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.usecase.HealthCenterFacade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HealthCenterRepository
        extends JpaRepository<HealthCenter, String>, JpaSpecificationExecutor<HealthCenterFacade> {
}
