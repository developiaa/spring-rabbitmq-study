package study.developia.api.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final Producer producer;

    @PostMapping("/send")
    public void send(@RequestBody Message message) {
        log.info("message={}, {}", message.getTitle(), message.getMessage());
        producer.sendMessage(message);
    }

    @PostMapping("/send2")
    public void send2(@RequestBody Message message) {
        log.info("message2={}, {}", message.getTitle(), message.getMessage());
        producer.sendMessageByFanout(message);
    }

    @PostMapping("/send3")
    public void send3(@RequestBody Message message) {
        log.info("message3={}, {}", message.getTitle(), message.getMessage());
        producer.sendMessage2(message);
    }
}
