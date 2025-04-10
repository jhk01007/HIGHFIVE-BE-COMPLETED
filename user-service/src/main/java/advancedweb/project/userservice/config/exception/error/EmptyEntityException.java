package advancedweb.project.userservice.config.exception.error;

public class EmptyEntityException extends RuntimeException {
    public EmptyEntityException() {
        super("존재하지 않는 유저입니다.");
    }
}
