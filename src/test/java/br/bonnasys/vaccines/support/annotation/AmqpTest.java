package br.bonnasys.vaccines.support.annotation;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Inherited
@SpringBootTest
@Tag("integrationTest")
@ActiveProfiles("test")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AmqpTest {

}
