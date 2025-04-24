package advancedweb.project.userservice.application.usecase;

import advancedweb.project.userservice.application.dto.request.LoginReq;
import advancedweb.project.userservice.application.dto.request.SignUpReq;
import advancedweb.project.userservice.application.dto.response.AuthRes;
import advancedweb.project.userservice.global.exception.RestApiException;
import advancedweb.project.userservice.global.config.security.TokenProvider;
import advancedweb.project.userservice.domain.entity.User;
import advancedweb.project.userservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static advancedweb.project.userservice.global.exception.code.status.AuthErrorStatus.INVALID_ACCESS_TOKEN;
import static advancedweb.project.userservice.global.exception.code.status.AuthErrorStatus.INVALID_ID_TOKEN;
import static advancedweb.project.userservice.global.exception.code.status.GlobalErrorStatus._EXIST_USERNAME;

@Service
@RequiredArgsConstructor
public class UserAuthUseCase {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    /**
     *  Public Method
     */
    @Transactional
    public AuthRes signUp(SignUpReq request) {
        checkAlreadyRegistered(request);
        User user = userService.save(request);
        return new AuthRes(tokenProvider.createAccessToken(user.getUserNo()));
    }

    public AuthRes login(LoginReq request) {
        User user = userService.findByLoginReq(request);
        return new AuthRes(tokenProvider.createAccessToken(user.getUserNo()));
    }

    public String validateToken(String token) {
        if (!tokenProvider.validateToken(token))
            throw new RestApiException(INVALID_ACCESS_TOKEN);

        return tokenProvider.getId(token)
                .orElseThrow(() -> new RestApiException(INVALID_ID_TOKEN));
    }


    /**
     *  Private Method
     */
    private void checkAlreadyRegistered(SignUpReq request) {
        if (userService.existsByUsername(request.username()))
            throw new RestApiException(_EXIST_USERNAME);
    }
}
