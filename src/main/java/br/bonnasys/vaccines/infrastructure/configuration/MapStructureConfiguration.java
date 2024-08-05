package br.bonnasys.vaccines.infrastructure.configuration;

import br.bonnasys.vaccines.app.patients.mapper.PatientMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructureConfiguration {

    @Bean
    public PatientMapper patientMapper() {
        return PatientMapper.INSTANCE;
    }
}
