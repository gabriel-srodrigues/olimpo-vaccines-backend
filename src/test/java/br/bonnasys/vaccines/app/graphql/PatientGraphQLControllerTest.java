package br.bonnasys.vaccines.app.graphql;

import br.bonnasys.vaccines.app.dto.response.PaginationResponse;
import br.bonnasys.vaccines.app.dto.response.PatientResponse;
import br.bonnasys.vaccines.domain.model.Patient;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.get.GetPatientByIdUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.SearchPatientUseCase;
import br.bonnasys.vaccines.domain.usecase.patient.retrieve.search.history.SearchPatientHistoryUseCase;
import br.bonnasys.vaccines.infrastrucutre.configuration.MapStructConfiguration;
import br.bonnasys.vaccines.support.builder.PatientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.GraphQlTester.Response;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

@ActiveProfiles("test")
@GraphQlTest(includeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MapStructConfiguration.class))
class PatientGraphQLControllerTest {

    @MockBean
    private SearchPatientUseCase searchPatientUseCase;

    @MockBean
    private GetPatientByIdUseCase getPatientByIdUseCase;

    @MockBean
    private SearchPatientHistoryUseCase searchPatientHistoryUseCase;

    @Autowired
    private GraphQlTester graphql;

    @Test
    void givenValidCommandWhenCallsSearchPatientsThenAssertFields() {
        String expectedName = "Gabriel Rodrigues";
        Integer expectedPage = 0;
        Integer expectedSize = 10;

        Patient patient = PatientBuilder.any()
                .name(expectedName)
                .withId()
                .build();

        Page<Patient> patientPage = new PageImpl<>(List.of(patient), PageRequest.of(0, 10), 1);
        Mockito.when(searchPatientUseCase.execute(Mockito.any(), Mockito.any())).thenReturn(patientPage);

        final var query = """
                query search($page: Int, $size: Int, $name: String, $email: String) {
                  searchPatients(page: $page, size: $size, name: $name, email: $email) {
                    content {
                      id
                      name
                      email
                    }
                    page {
                          pageSize
                          pageNumber
                          totalPages
                          totalElements
                    }
                  }
                }
                """;
        Response response = this.graphql.document(query)
                .variable("page", expectedPage)
                .variable("size", expectedSize)
                .variable("name", expectedName)
                .execute();

        var actualResponse = response.path("searchPatients")
                .entity(PaginationResponse.class)
                .get();

        Assertions.assertEquals(expectedPage, actualResponse.getPage().pageNumber());
        Assertions.assertEquals(expectedSize, actualResponse.getPage().pageSize());
        Assertions.assertNotNull(actualResponse.getContent());

    }


    @Test
    void givenValidCommandWithHistoryWhenCallsGetPatientByIdThenAssertFields() {
        String id = UUID.randomUUID().toString();
        Patient patient = PatientBuilder.any().withId(id).build();
        Mockito.when(getPatientByIdUseCase.execute(id)).thenReturn(patient);

        final var query = """
                query get($id: String) {
                  getPatientById(id: $id) {
                      id
                      name
                      phone
                      history {
                        id
                        registrationDate
                        healthCenter { name id }
                        vaccine { producer id name }
                      }
                  }
                }
                """;

        Response response = this.graphql.document(query)
                .variable("id", id)
                .execute();

        var actualResponse = response.path("getPatientById")
                .entity(PatientResponse.class)
                .get();

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(id, actualResponse.getId());
        Mockito.verify(searchPatientHistoryUseCase, Mockito.times(1)).execute(id);
    }

    @Test
    void givenValidCommandWithoutHistoryWhenCallsGetPatientByIdThenAssertFields() {
        String id = UUID.randomUUID().toString();
        Patient patient = PatientBuilder.any().withId(id).build();
        Mockito.when(getPatientByIdUseCase.execute(id)).thenReturn(patient);

        final var query = """
                query get($id: String) {
                  getPatientById(id: $id) {
                      id
                      name
                      phone
                  }
                }
                """;

        Response response = this.graphql.document(query)
                .variable("id", id)
                .execute();

        var actualResponse = response.path("getPatientById")
                .entity(PatientResponse.class)
                .get();

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(id, actualResponse.getId());
        Mockito.verify(getPatientByIdUseCase, Mockito.times(1)).execute(Mockito.any(String.class));
        Mockito.verify(searchPatientHistoryUseCase, Mockito.times(1)).execute(id);
    }
}