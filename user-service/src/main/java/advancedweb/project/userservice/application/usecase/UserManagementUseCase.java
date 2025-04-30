package advancedweb.project.userservice.application.usecase;

import advancedweb.project.userservice.application.dto.request.ProfileReq;
import advancedweb.project.userservice.application.dto.response.ProfileRes;
import advancedweb.project.userservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementUseCase {

    private final UserService userService;

    public ProfileRes updateProfile(ProfileReq request, String userNo) {
        return null;
    }
}
