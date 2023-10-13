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

    // 똑같은 queue가 있는 경우 라운드 로빈 방식으로 실행
    // 서버가 여러대라면 이 역시 라운드 로빈으로 동작
    @RabbitListener(queues = "push.queue", concurrency = "1")
    public void consume(MessageDto messageDto) {
        log.info("Consume message {}", messageDto);

        messageRepository.save(
                Message.builder()
                        .title(messageDto.getTitle())
                        .message(messageDto.getMessage())
                        .build()
        );
    }

    @RabbitListener(queues = "push.queue", concurrency = "1")
    public void consume3(MessageDto messageDto) {
        log.info("Consume3 message {}", messageDto);

        messageRepository.save(
                Message.builder()
                        .title(messageDto.getTitle())
                        .message(messageDto.getMessage())
                        .build()
        );
    }

    @RabbitListener(queues = "hello.queue", concurrency = "2")
    public void consume2(MessageDto messageDto) {
        log.info("HelloQueue consume message {}", messageDto);

        messageRepository.save(
                Message.builder()
                        .title(messageDto.getTitle())
                        .message(messageDto.getMessage())
                        .build()
        );
    }


}
