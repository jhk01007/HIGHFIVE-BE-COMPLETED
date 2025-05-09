package advancedweb.project.userservice.global.exception.code.status;

import advancedweb.project.userservice.global.exception.code.BaseCodeDto;
import advancedweb.project.userservice.global.exception.code.BaseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorStatus implements BaseCodeInterface {

    EMPTY_JWT(HttpStatus.UNAUTHORIZED, "AUTH001", "JWT가 없습니다."),
    EXPIRED_MEMBER_JWT(HttpStatus.UNAUTHORIZED, "AUTH002", "만료된 JWT입니다."),
    UNSUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "AUTH003", "지원하지 않는 JWT입니다."),

    _EMPTY_USERNAME_PASSWORD(HttpStatus.BAD_REQUEST, "AUTH007", "아이디 혹은 비밀번호를 입력해주세요."),
    INVALID_ID_TOKEN(HttpStatus.BAD_REQUEST, "AUTH004", "유효하지 않은 ID TOKEN입니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "AUTH005", "만료된 REFRESH TOKEN입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "AUTH005", "유효하지 않은 ACCESS TOKEN입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "AUTH006", "유효하지 않은 REFRESH TOKEN입니다.")
    ;

    private final HttpStatus httpStatus;
    private final boolean isSuccess = false;
    private final String code;
    private final String message;

    @Override
    public BaseCodeDto getCode() {
        return BaseCodeDto.builder()
                .httpStatus(httpStatus)
                .isSuccess(isSuccess)
                .code(code)
                .message(message)
                .build();
    }
}
