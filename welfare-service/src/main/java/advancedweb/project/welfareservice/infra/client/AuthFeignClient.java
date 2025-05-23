package advancedweb.project.welfareservice.infra.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
@Retry(name = "authClient")
@CircuitBreaker(name = "authCircuit")
public interface AuthFeignClient {

    @GetMapping("/api/users/validate")
    Boolean validateToken(@RequestHeader("Authorization") String token);
}
