package advancedweb.project.welfareservice.infra.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenValidateEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public boolean send(String topic, String token) {
        try {
            Message message = Message.create(token);

            String jsonMessage = null;
                jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, jsonMessage);
            log.info("message sent: {}", jsonMessage);

            return true;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


/**
 *  Event Class
 */

@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class Metadata {

    private String eventNo;
    private String eventType;
    private String source;
    private String createdAt;

    public static Metadata create() {
        return Metadata.builder()
                .eventNo(UUID.randomUUID().toString())
                .eventType("TOKEN_VALIDATION")
                .source("welfare-service")
                .createdAt(LocalDateTime.now().toString())
                .build();
    }
}

@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class Payload {

    private String token;

    public static Payload create(String token) {
        return Payload.builder()
                .token(token)
                .build();
    }
}

@Data
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class Message {

    private Metadata metadata;
    private Payload payload;

    public static Message create(String token) {
        return Message.builder()
                .metadata(Metadata.create())
                .payload(Payload.create(token))
                .build();
    }
}
