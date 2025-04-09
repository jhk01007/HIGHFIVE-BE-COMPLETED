package advancedweb.project.userservice.config.exception.error;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("잘못된 토큰입니다.");
    }
}
