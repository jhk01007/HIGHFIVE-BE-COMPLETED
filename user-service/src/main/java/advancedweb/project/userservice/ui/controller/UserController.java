package advancedweb.project.userservice.ui.controller;

import advancedweb.project.userservice.application.dto.request.ProfileReq;
import advancedweb.project.userservice.application.dto.response.ProfileRes;
import advancedweb.project.userservice.application.usecase.UserInfoUseCase;
import advancedweb.project.userservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserInfoUseCase userInfoUseCase;

    /**
     * 인적사항 작성 API
     */
    public ResponseEntity<ProfileRes> saveProfile(@RequestBody ProfileReq request) {
        return ResponseEntity.ok(userInfoUseCase.saveProfile());
    }
}
