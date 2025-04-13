package advancedweb.project.userservice.ui.controller;

import advancedweb.project.userservice.application.dto.request.LoginReq;
import advancedweb.project.userservice.application.dto.request.SignUpReq;
import advancedweb.project.userservice.application.dto.response.AuthRes;
import advancedweb.project.userservice.application.usecase.UserAuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class AuthController {

    private final UserAuthUseCase userAuthUseCase;

    /**
     *  회원가입 API
     */
    @PostMapping("/sign-up")
    public ResponseEntity<AuthRes> signUp(@RequestBody SignUpReq request) {
        return ResponseEntity.ok(userAuthUseCase.signUp(request));
    }

    /**
     *  로그인 API
     */
    @PostMapping("/login")
    public ResponseEntity<AuthRes> login(@RequestBody LoginReq request) {
        return ResponseEntity.ok(userAuthUseCase.login(request));
    }

}
