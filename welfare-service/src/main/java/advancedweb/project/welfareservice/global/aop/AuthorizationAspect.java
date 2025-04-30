package advancedweb.project.welfareservice.global.aop;

import advancedweb.project.welfareservice.global.exception.RestApiException;
import advancedweb.project.welfareservice.global.security.TokenProvider;
import advancedweb.project.welfareservice.infra.client.AuthFeignClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static advancedweb.project.welfareservice.global.exception.code.status.GlobalErrorStatus._UNAUTHORIZED;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final TokenProvider tokenProvider;
    private final AuthFeignClient authFeignClient;

    @Around("@annotation(advancedweb.project.welfareservice.global.annotation.CheckAuthorization)")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = tokenProvider.getToken(request)
                .orElseThrow(() -> new RestApiException(_UNAUTHORIZED));

        if (!authFeignClient.validateToken(token)) {
            throw new RestApiException(_UNAUTHORIZED);
        }

        return joinPoint.proceed();
    }
}
