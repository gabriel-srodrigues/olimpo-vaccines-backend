package br.bonnasys.vaccines.app.patients.mapper;

import br.bonnasys.vaccines.app.patients.dto.response.PatientResponse;
import br.bonnasys.vaccines.domain.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientResponse toPatientResponse(Patient patient);

    @Mapping(target = "history", ignore = true)
    PatientResponse toPatientResponseWithoutHistory(Patient patient);
}
