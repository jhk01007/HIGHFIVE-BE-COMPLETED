package advancedweb.project.userservice.domain.service;

import advancedweb.project.userservice.application.dto.request.LoginReq;
import advancedweb.project.userservice.application.dto.request.SignUpReq;
import advancedweb.project.userservice.application.dto.response.ProfileRes;
import advancedweb.project.userservice.config.exception.error.EmptyEntityException;
import advancedweb.project.userservice.domain.entity.User;
import advancedweb.project.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User save(SignUpReq request) {
        return userRepository.save(
                User.create(request.nickname(), request.username(), request.password())
        );
    }

    public User findByLoginReq(LoginReq request) {
        return userRepository.findByUsernameAndPassword(request.username(), request.password())
                .orElseThrow(EmptyEntityException::new);
    }

}
