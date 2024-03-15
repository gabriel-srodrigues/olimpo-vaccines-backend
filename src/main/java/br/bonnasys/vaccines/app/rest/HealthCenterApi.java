package br.bonnasys.vaccines.app.rest;

import br.bonnasys.vaccines.app.rest.request.CreateHealthCenterRequest;
import br.bonnasys.vaccines.app.rest.request.ManageStockHealthCenterRequest;
import br.bonnasys.vaccines.app.rest.response.HealthCenterDetailedResponse;
import br.bonnasys.vaccines.app.rest.response.HealthCenterSummaryResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Health Center")
@RequestMapping("health-centers")
public interface HealthCenterApi {

    @PostMapping
    ResponseEntity<Void> create(@RequestBody CreateHealthCenterRequest request);

    @GetMapping
    ResponseEntity<Page<HealthCenterSummaryResponse>> search(@PageableDefault Pageable page);

    @GetMapping("{id}")
    ResponseEntity<HealthCenterDetailedResponse> get(@PathVariable String id);

    @PutMapping("{id}")
    ResponseEntity<HealthCenterDetailedResponse> insertToStock(@PathVariable String id,
                                                               @RequestBody ManageStockHealthCenterRequest request);

    @DeleteMapping("{id}")
    ResponseEntity<HealthCenterDetailedResponse> removeOfStock(@PathVariable String id,
                                                               @RequestBody ManageStockHealthCenterRequest request);
}
