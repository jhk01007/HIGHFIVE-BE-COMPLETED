package advancedweb.project.userservice.application.usecase;

import advancedweb.project.userservice.application.dto.response.ProfileRes;
import advancedweb.project.userservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoUseCase {

    private final UserService userService;

    public ProfileRes saveProfile() {
        // 인적사항 DB에 저장
        return null;
    }
}
