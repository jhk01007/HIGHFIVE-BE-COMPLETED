package advancedweb.project.welfareservice.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface AuthFeignClient {

    @GetMapping("/api/users/validate")
    Boolean validateToken(@RequestHeader("Authorization") String token);
}
