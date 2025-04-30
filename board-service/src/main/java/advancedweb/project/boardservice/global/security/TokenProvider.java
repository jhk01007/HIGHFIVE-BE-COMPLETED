package advancedweb.project.boardservice.global.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;

    private static final String TOKEN_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String ID_CLAIM = "id";

    public Optional<String> getId(String token) {
        try {
            return Optional.ofNullable(getClaims(token).get(ID_CLAIM, String.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(TOKEN_HEADER))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getKey().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
