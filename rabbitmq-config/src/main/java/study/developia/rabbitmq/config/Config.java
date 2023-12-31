package study.developia.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Config {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Bean
    public Queue queue() {
        return new Queue("push.queue", true);
    }

//    @Bean
//    DirectExchange directExchange() {
//        return new DirectExchange("hello.exchange");
//    }

    //    @Bean
//    Binding binding(DirectExchange directExchange, Queue queue) {
//        return BindingBuilder.bind(queue).to(directExchange).with("hello.key");
//    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("push");
    }

    @Bean
    public Binding binding(TopicExchange topicExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with("partner.*");
    }

    // config 설정 없이도 생성만 되어 있다면 사용할 수 있다.(Producer.java)
//    @Bean
//    Queue fanoutQueue() {
//        return new Queue("fanout.queue", false);
//    }
//
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange("fanout");
//    }
//
//    @Bean
//    Binding fanoutBinding(FanoutExchange fanoutExchange, Queue queue) {
//        return BindingBuilder.bind(queue).to(fanoutExchange);
//    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange("push");
        return rabbitTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
