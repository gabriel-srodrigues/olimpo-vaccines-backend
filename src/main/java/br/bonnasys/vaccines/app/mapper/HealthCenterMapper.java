package br.bonnasys.vaccines.app.mapper;

import br.bonnasys.vaccines.app.rest.request.CreateHealthCenterRequest;
import br.bonnasys.vaccines.app.rest.request.ManageStockHealthCenterRequest;
import br.bonnasys.vaccines.app.rest.response.HealthCenterDetailedResponse;
import br.bonnasys.vaccines.app.rest.response.HealthCenterSummaryResponse;
import br.bonnasys.vaccines.domain.model.HealthCenter;
import br.bonnasys.vaccines.domain.model.Vaccine;
import br.bonnasys.vaccines.domain.usecase.healthcenter.create.CreateHealthCenterCommand;
import br.bonnasys.vaccines.domain.usecase.healthcenter.update.ManageHealthCenterCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface HealthCenterMapper {
    HealthCenterMapper INSTANCE = Mappers.getMapper(HealthCenterMapper.class);

    @Mapping(target = "stock", defaultExpression = "java(setStockAsList(healthCenter.getStock()))")
    HealthCenterDetailedResponse toDetailedResponse(HealthCenter healthCenter);

    HealthCenterSummaryResponse toSummaryResponse(HealthCenter healthCenter);

    CreateHealthCenterCommand requestToCommand(CreateHealthCenterRequest request);

    @Mapping(target = "healthCenterId", source = "id")
    ManageHealthCenterCommand manageStockRequestToCommand(String id, ManageStockHealthCenterRequest request);

    default List<HealthCenterDetailedResponse.Stock> setStockAsList(Map<Vaccine, Integer> stock) {
        return stock.entrySet().stream()
                .map(map ->
                    new HealthCenterDetailedResponse.Stock(
                            map.getKey().getId(),
                            map.getKey().getName(),
                            map.getValue()))
                .toList();
    }

}
