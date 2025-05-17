package advancedweb.project.boardservice.global.aop;

import advancedweb.project.boardservice.global.exception.RestApiException;
import advancedweb.project.boardservice.global.cache.AuthService;
import advancedweb.project.boardservice.global.security.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static advancedweb.project.boardservice.global.exception.code.status.GlobalErrorStatus._UNAUTHORIZED;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final TokenProvider tokenProvider;
    private final AuthService authService;

    @Around("@annotation(advancedweb.project.boardservice.global.annotation.CheckAuthorization)")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = tokenProvider.getToken(request)
                .orElseThrow(() -> new RestApiException(_UNAUTHORIZED));

        if (!authService.isTokenValid(token)) {
            throw new RestApiException(_UNAUTHORIZED);
        }

        return joinPoint.proceed();
    }
}
