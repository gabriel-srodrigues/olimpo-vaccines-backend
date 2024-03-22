package br.bonnasys.vaccines.app.rest;


import br.bonnasys.vaccines.app.rest.request.CreateVaccineRequest;
import br.bonnasys.vaccines.app.rest.response.VaccineResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Vaccines")
@RequestMapping("vaccines")
public interface VaccineApi {

    @PostMapping
    ResponseEntity<Void> create(@RequestBody CreateVaccineRequest request);

    @GetMapping
    ResponseEntity<Page<VaccineResponse>> search(@PageableDefault Pageable pageable);

    @GetMapping("{id}")
    ResponseEntity<VaccineResponse> getById(@PathVariable String id);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable String id);
}
