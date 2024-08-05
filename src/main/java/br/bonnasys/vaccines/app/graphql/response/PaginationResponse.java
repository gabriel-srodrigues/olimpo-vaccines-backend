package br.bonnasys.vaccines.app.graphql.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> content;
    private PageResponse page;

}
