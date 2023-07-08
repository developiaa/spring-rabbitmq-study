package study.developia.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import study.developia.consumer.entity.Message;
import study.developia.consumer.dto.MessageDto;
import study.developia.consumer.repository.MessageRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    @RabbitListener(queues = "hello.queue")
    public void consume(MessageDto messageDto) {
        log.info("Consume message {}", messageDto);

        messageRepository.save(
                Message.builder()
                        .title(messageDto.getTitle())
                        .message(messageDto.getMessage())
                        .build()
        );
    }


}
