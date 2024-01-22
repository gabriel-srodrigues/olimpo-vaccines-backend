package br.bonnasys.vaccines.app.patients.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageResponse {
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;

}
