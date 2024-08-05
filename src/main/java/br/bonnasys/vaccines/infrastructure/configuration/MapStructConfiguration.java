package br.bonnasys.vaccines.infrastructure.configuration;

import br.bonnasys.vaccines.app.mapper.HealthCenterMapper;
import br.bonnasys.vaccines.app.mapper.PatientMapper;
import br.bonnasys.vaccines.app.mapper.VaccineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MapStructConfiguration {

    @Bean
    public PatientMapper patientMapper() {
        log.info("[patientMapper]: Starting patient mapper");
        return PatientMapper.INSTANCE;
    }

    @Bean
    public HealthCenterMapper healthCenterMapper() {
        log.info("[healthCenterMapper]: starting health center mapper");
        return HealthCenterMapper.INSTANCE;
    }

    @Bean
    public VaccineMapper vaccineMapper() {
        log.info("[vaccineMapper]: starting vaccine mapper");
        return VaccineMapper.INSTANCE;
    }
}
