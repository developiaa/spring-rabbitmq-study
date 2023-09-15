package study.developia.api.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Message message) {
//        rabbitTemplate.convertAndSend("hello.exchange2", "hello.key", message);
        rabbitTemplate.convertAndSend("partner." + message.getMessage(), message);
    }

    public void sendMessageByFanout(Message message) {
        rabbitTemplate.convertAndSend("fanout", null, message);
    }

    public void sendMessage2(Message message) {
        for (int i = 0; i < 500; i++) {
            message.setMessage(String.valueOf(i));
            rabbitTemplate.convertAndSend("hello.exchange2", "hello.key", message);
        }
    }
}
