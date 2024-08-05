package br.bonnasys.vaccines.app.graphql.response;


public record PageResponse(Integer pageNumber,
                           Integer pageSize,
                           Long totalElements,
                           Integer totalPages) {
}
