package advancedweb.project.userservice.infra.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenValidateEventConsumer {

    @KafkaListener(topics = "welfare.token-validate")
    public void listen(String kafkaMessage) {
        log.info(kafkaMessage);
    }
}
