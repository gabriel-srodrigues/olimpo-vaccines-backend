package br.bonnasys.vaccines.app.mapper;

import br.bonnasys.vaccines.app.graphql.response.PatientResponse;
import br.bonnasys.vaccines.app.graphql.response.VaccineRegistrationResponse;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.model.VaccineRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mapping(target = "history", ignore = true)
    PatientResponse toPatientResponse(Patient patient);

    VaccineRegistrationResponse toVaccineRegistrationResponse(VaccineRegistration vaccineRegistration);
}
