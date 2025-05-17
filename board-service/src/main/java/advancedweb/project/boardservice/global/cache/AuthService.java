package advancedweb.project.boardservice.global.cache;

import advancedweb.project.boardservice.infra.client.AuthFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthFeignClient authFeignClient;

    @Cacheable(
            cacheNames = "auth",
            key = "'VALID_TOKEN:' + #token",
            cacheManager = "cacheManager"
    )
    public boolean isTokenValid(String token) {
        return authFeignClient.validateToken(token);
    }
}
