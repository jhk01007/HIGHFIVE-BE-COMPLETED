package advancedweb.project.userservice.domain.service;

import advancedweb.project.userservice.application.dto.request.LoginReq;
import advancedweb.project.userservice.application.dto.request.SignUpReq;
import advancedweb.project.userservice.global.exception.RestApiException;
import advancedweb.project.userservice.domain.entity.User;
import advancedweb.project.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static advancedweb.project.userservice.global.exception.code.status.GlobalErrorStatus._EXIST_ENTITY;
import static advancedweb.project.userservice.global.exception.code.status.GlobalErrorStatus._LOGIN_ERROR;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User save(SignUpReq request) {
        return userRepository.save(
                User.create(request.username(), request.password())
        );
    }

    public User findByLoginReq(LoginReq request) {
        return userRepository.findByUsernameAndPassword(request.username(), request.password())
                .orElseThrow(() -> new RestApiException(_LOGIN_ERROR));
    }

    public Boolean existsByUserNo(String userNo) {
        return userRepository.existsById(userNo);
    }
}
