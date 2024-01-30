package br.bonnasys.vaccines.app.dto.response;


public record PageResponse(Integer pageNumber,
                           Integer pageSize,
                           Long totalElements,
                           Integer totalPages) {
}
