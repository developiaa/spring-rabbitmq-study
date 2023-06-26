package study.developia.api.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Receiver {
    @RabbitListener(queues = "hello.queue")
    public void consume(Message message) {
        log.info("Consume message {}", message);
    }
}
