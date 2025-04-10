package advancedweb.project.userservice.config.exception.error;

public class ExistsUsernameException extends RuntimeException {
    public ExistsUsernameException() {
        super("이미 사용 중인 아이디입니다.");
    }
}
