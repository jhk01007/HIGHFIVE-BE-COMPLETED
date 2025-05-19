package advancedweb.project.welfareservice.global.ratelimiter;

import advancedweb.project.welfareservice.global.exception.RestApiException;
import advancedweb.project.welfareservice.global.security.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static advancedweb.project.welfareservice.global.exception.code.status.GlobalErrorStatus._TOO_MANY_REQUEST;
import static advancedweb.project.welfareservice.global.exception.code.status.GlobalErrorStatus._UNAUTHORIZED;

@Setter
@Component
@RequiredArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {
    private final RequestRateLimiter requestRateLimiter;
    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userNo = tokenProvider.getToken(request)
                .flatMap(tokenProvider::getId)
                .orElseThrow(() -> new RestApiException(_UNAUTHORIZED));

        if (!requestRateLimiter.isRequestAllowed(userNo, request.getRequestURI()))
            throw new RestApiException(_TOO_MANY_REQUEST);

        return true;
    }
}
