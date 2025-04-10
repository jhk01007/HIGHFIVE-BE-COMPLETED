package advancedweb.project.userservice.application.usecase;

import advancedweb.project.userservice.application.dto.request.LoginReq;
import advancedweb.project.userservice.application.dto.request.SignUpReq;
import advancedweb.project.userservice.application.dto.response.AuthRes;
import advancedweb.project.userservice.config.exception.error.ExistsUsernameException;
import advancedweb.project.userservice.config.security.TokenProvider;
import advancedweb.project.userservice.domain.entity.User;
import advancedweb.project.userservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



    /**
     *  Private Method
     */
    private void checkAlreadyRegistered(SignUpReq request) {
        if (userService.existsByUsername(request.username()))
            throw new ExistsUsernameException();
    }
}
