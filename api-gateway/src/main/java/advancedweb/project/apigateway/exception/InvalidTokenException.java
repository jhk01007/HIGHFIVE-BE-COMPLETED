package advancedweb.project.apigateway.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("만료되거나 사용할 수 없는 토큰입니다.");
    }
}
