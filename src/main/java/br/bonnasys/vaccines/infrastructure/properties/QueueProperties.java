package br.bonnasys.vaccines.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
@Getter
@Setter
@ToString
public class QueueProperties implements InitializingBean {
    private String exchange;
    private String routingKey;
    private String queue;

    @Override
    public void afterPropertiesSet() {
        log.info(toString());
    }
}
