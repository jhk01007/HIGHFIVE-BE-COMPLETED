package advancedweb.project.welfareservice.global.exception.error;

public class KafkaMessageException extends RuntimeException {
    public KafkaMessageException() {
        super("Kafka 메세지 전송 오류");
    }
}
