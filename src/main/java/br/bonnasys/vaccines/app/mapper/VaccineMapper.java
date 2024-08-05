package br.bonnasys.vaccines.app.mapper;

import br.bonnasys.vaccines.app.rest.response.VaccineResponse;
import br.bonnasys.vaccines.domain.model.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VaccineMapper {
    VaccineMapper INSTANCE = Mappers.getMapper(VaccineMapper.class);

    VaccineResponse domainObjectToResponse(Vaccine vaccine);
}
