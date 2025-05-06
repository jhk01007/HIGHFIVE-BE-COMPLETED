package advancedweb.project.userservice.ui.controller;

import advancedweb.project.userservice.application.dto.request.ProfileReq;
import advancedweb.project.userservice.application.dto.response.ProfileRes;
import advancedweb.project.userservice.application.usecase.UserManagementUseCase;
import advancedweb.project.userservice.global.annotation.CurrentUser;
import advancedweb.project.userservice.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserManagementUseCase userManagementUseCase;

    /**
     * 인적사항 작성 API
     * 내 정보로 입력한 나의 나이 및 지역 수정
     */
    @PatchMapping
    public BaseResponse<ProfileRes> updateProfile(@RequestBody ProfileReq request,
                                                  @CurrentUser @Parameter(hidden = true) String userNo) {
        return BaseResponse.onSuccess(userManagementUseCase.updateProfile(request, userNo));
    }
}
