package br.bonnasys.vaccines.support.annotations;

import br.bonnasys.vaccines.infrastructure.configuration.JacksonConfiguration;
import br.bonnasys.vaccines.infrastructure.configuration.MapStructureConfiguration;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test")
@GraphQlTest(includeFilters =
@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        MapStructureConfiguration.class,
        JacksonConfiguration.class
}))
public @interface GraphQLControllerTest {

    @AliasFor(annotation = GraphQlTest.class, attribute = "controllers")
    Class<?>[] controllers() default {};
}
