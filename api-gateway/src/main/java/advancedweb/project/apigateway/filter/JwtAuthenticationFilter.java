package advancedweb.project.apigateway.filter;

import advancedweb.project.apigateway.config.security.jwt.TokenProvider;
import advancedweb.project.apigateway.config.security.path.ExcludeAuthPathProperties;
import advancedweb.project.apigateway.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final TokenProvider tokenProvider;
    private final ExcludeAuthPathProperties excludeAuthPathProperties;
    private static final PathPatternParser pathPatternParser = new PathPatternParser();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            ServerHttpRequest request = exchange.getRequest();

            if(isExcludedPath(request)) {
                chain.filter(exchange);
                return Mono.empty();
            }

            tokenProvider.getToken(request).ifPresentOrElse(token -> {
                if(tokenProvider.validateToken(token))
                    setAuthentication(token);
                else throw new InvalidTokenException();
            }, () -> {
                throw new InvalidTokenException();
            });

            return chain.filter(exchange);

        } catch (InvalidTokenException e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);

            String body = "{\"message\": \"Invalid or expired token.\"}";
            DataBuffer buffer = response.bufferFactory()
                    .wrap(body.getBytes(StandardCharsets.UTF_8));
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

            return response.writeWith(Mono.just(buffer));
        }
    }

    public boolean isExcludedPath(ServerHttpRequest request) {
        String requestPath = request.getPath().toString();
        HttpMethod requestMethod = request.getMethod();

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
