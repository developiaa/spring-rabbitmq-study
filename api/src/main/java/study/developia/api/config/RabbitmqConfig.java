package study.developia.api.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.developia.rabbitmq.config.Config;


@Configuration
public class RabbitmqConfig {
    @Bean
    RabbitTemplate testRabbitTemplate() {
        Config config = new Config();
        return config.rabbitTemplate(config.connectionFactory(), config.messageConverter());
    }
}
