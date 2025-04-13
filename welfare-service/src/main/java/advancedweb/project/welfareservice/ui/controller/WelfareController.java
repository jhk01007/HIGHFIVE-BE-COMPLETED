package advancedweb.project.welfareservice.ui.controller;

import advancedweb.project.welfareservice.infra.client.AuthFeignClient;
import advancedweb.project.welfareservice.infra.kafka.producer.TokenValidateEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/welfare")
public class WelfareController {

    private final TokenValidateEventProducer tokenValidateEventProducer;
    private final AuthFeignClient authFeignClient;

    @GetMapping("/kafka-test")
    public ResponseEntity<String> kafkaTest(@RequestHeader("Authorization") String token) {
        return authFeignClient.validateToken(token);
//        return ResponseEntity.ok(tokenValidateEventProducer.send("welfare.token-validate", "accessToken"));
    }
}
