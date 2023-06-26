package study.developia.api.rabbitmq;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {
    private String title;
    private String message;
}
