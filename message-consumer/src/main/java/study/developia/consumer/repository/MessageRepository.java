package study.developia.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.developia.consumer.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
