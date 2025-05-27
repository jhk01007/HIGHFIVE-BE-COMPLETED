package advancedweb.project.userservice.ui.controller;

import advancedweb.project.userservice.application.usecase.UserAuthUseCase;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class InternalAuthController {

    private final UserAuthUseCase userAuthUseCase;

    @Hidden
    @GetMapping("/validate")
    public Boolean validateToken(@RequestHeader("Authorization") String token) {
        return userAuthUseCase.validateToken(token);
    }
}
