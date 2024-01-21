package br.bonnasys.vaccines.app.graphql.patients.response;

import br.bonnasys.vaccines.domain.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> content;
    private PageResponse page;

}
