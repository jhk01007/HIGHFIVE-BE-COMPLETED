package advancedweb.project.userservice.global.config.security;

import advancedweb.project.userservice.global.exception.code.status.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.PathContainer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.pattern.PathPatternParser;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final ExcludeAuthPathProperties excludeAuthPathProperties;

    private static final PathPatternParser pathPatternParser = new PathPatternParser();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (isExcludedPath(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            tokenProvider.getToken(request).ifPresentOrElse(token -> {
                if (tokenProvider.validateToken(token))
                    setAuthentication(token);
                else throw new InvalidTokenException();
            },()-> {
                throw new InvalidTokenException();
            });

            filterChain.doFilter(request, response);
        } catch (InvalidTokenException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");

            String jsonResponse = String.format("{\"message\": \"%s\"}", e.getMessage());

            PrintWriter writer = response.getWriter();
            writer.write(jsonResponse);
            writer.flush();
            writer.close();
        }
    }

    public boolean isExcludedPath(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        HttpMethod requestMethod = HttpMethod.valueOf(request.getMethod());

        return excludeAuthPathProperties.getPaths().stream()
                .anyMatch(authPath ->
                        pathPatternParser.parse(authPath.getPathPattern())
                                .matches(PathContainer.parsePath(requestPath))
                        && requestMethod.equals(HttpMethod.valueOf(authPath.getMethod()))
                );
    }

    private void setAuthentication(String token) {
        if (tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
