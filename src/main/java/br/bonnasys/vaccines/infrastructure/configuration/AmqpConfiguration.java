package br.bonnasys.vaccines.infrastructure.configuration;

import br.bonnasys.vaccines.infrastructure.annotation.VaccineCreateQueue;
import br.bonnasys.vaccines.infrastructure.annotation.VaccineEvents;
import br.bonnasys.vaccines.infrastructure.properties.QueueProperties;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {

    @Bean
    @VaccineCreateQueue
    @ConfigurationProperties("amqp.queues.vaccines-create")
    QueueProperties videoCreatedQueueProperties() {
        return new QueueProperties();
    }

    @Configuration
    static class Admin {

        @Bean
        @VaccineEvents
        DirectExchange videoEventsExchange(@VaccineCreateQueue QueueProperties props) {
            return new DirectExchange(props.getExchange());
        }

        @Bean
        @VaccineCreateQueue
        Queue videoCreatedQueue(@VaccineCreateQueue QueueProperties props) {
            return new Queue(props.getQueue());
        }

        @Bean
        @VaccineCreateQueue
        Binding videoCreatedQueueBinding(
                @VaccineEvents DirectExchange exchange,
                @VaccineCreateQueue Queue queue,
                @VaccineCreateQueue QueueProperties props
        ) {
            return BindingBuilder.bind(queue).to(exchange).with(props.getRoutingKey());
        }
    }
}
