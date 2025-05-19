package advancedweb.project.welfareservice.global.ratelimiter.path;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@AllArgsConstructor
@ConfigurationProperties("include-ratelimiter-path-patterns")
public class IncludeRateLimitPathProperties {
    private List<AuthPath> paths;

    public List<String> getIncludePaths() {
        return paths.stream()
                .map(AuthPath::getPathPattern)
                .toList();
    }

    @Getter
    @AllArgsConstructor
    public static class AuthPath {
        private String pathPattern;
        private String method;
    }
}
