package br.bonnasys.vaccines.app.patients.graphql;

import br.bonnasys.vaccines.app.patients.dto.response.PaginationResponse;
import br.bonnasys.vaccines.app.patients.dto.response.PatientResponse;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.patient.PatientFacade;
import br.bonnasys.vaccines.support.annotations.GraphQLControllerTest;
import br.bonnasys.vaccines.support.builder.PatientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

@GraphQLControllerTest(controllers = PatientGraphQLController.class)
class PatientGraphQLControllerTest {

    @MockBean
    private PatientFacade patientFacade;

    @Autowired
    private GraphQlTester graphql;

    @Test
    void givenSearchPatientsWIthPageAndSizeWhenCallsGraphQLThenAssertFields() {
        final String expectedName = "Gabriel Rodrigues";
        final int expectedPage = 0;
        final int expectedSize = 10;

        Patient patient = PatientBuilder.any()
                .withId()
                .name(expectedName)
                .build();

        Page<Patient> patientPage = new PageImpl<>(List.of(patient), PageRequest.of(expectedPage, expectedSize), 1);
        Mockito.when(patientFacade.searchPatientsUseCase(Mockito.any())).thenReturn(patientPage);

        final var query = """
                query search($page: Int, $size: Int, $term: String) {
                    searchPatients(page: $page, size: $size, term: $term) {
                        content {
                            id
                            name
                            email
                            phone
                            birthdate
                            created_at: createdAt
                            updated_at: updatedAt
                        }
                        page {
                            page_size: pageSize
                            page_number: pageNumber
                            total_pages: totalPages
                            total_elements: totalElements
                        }
                    }
                }
                """;

        final var response = graphql.document(query)
                .variable("page", expectedPage)
                .variable("size", expectedSize)
                .execute();

        final var actualResponse = response
                .path("searchPatients")
                .entity(PaginationResponse.class)
                .get();

        Assertions.assertEquals(expectedSize, actualResponse.getPage().getPageSize());
        Assertions.assertEquals(expectedPage, actualResponse.getPage().getPageNumber());
        Assertions.assertEquals(1, actualResponse.getPage().getTotalElements());
        Assertions.assertEquals(1, actualResponse.getPage().getTotalPages());

        final List<PatientResponse> actualPatients = response
                .path("searchPatients.content")
                .entity(new ParameterizedTypeReference<List<PatientResponse>>() {})
                .get();

        Assertions.assertNotNull(actualPatients);
        Assertions.assertNotNull(expectedName, actualPatients.getFirst().getName());
    }

    @Test
    void givenCountPatientsWhenCallsGraphQLThenAssertActualResult() {
        long expectedCountResult = 4L;
        Mockito.when(patientFacade.countPatientsUseCase(Mockito.any())).thenReturn(expectedCountResult);

        final var query = """
                {
                	countPatients
                }
                """;

        final var response = graphql.document(query).execute();
        final var actualCount = response
                .path("countPatients")
                .entity(Long.class)
                .get();

        Assertions.assertEquals(expectedCountResult, actualCount);
    }
}