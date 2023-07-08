package study.developia.api.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final Producer producer;

    @PostMapping("/send")
    public void send(@RequestBody Message message) {
        producer.sendMessage(message);
    }
}
