package advancedweb.project.userservice.ui.controller;

import advancedweb.project.userservice.application.dto.request.ProfileReq;
import advancedweb.project.userservice.application.dto.response.ProfileRes;
import advancedweb.project.userservice.application.usecase.UserInfoUseCase;
import advancedweb.project.userservice.domain.service.UserService;
import advancedweb.project.userservice.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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
     * 내 정보로 입력한 나의 나이 및 지역 수정
     */
    @PatchMapping
    public BaseResponse<ProfileRes> updateProfile(@RequestBody ProfileReq request) {
        return BaseResponse.onSuccess(userInfoUseCase.saveProfile());
    }
}
