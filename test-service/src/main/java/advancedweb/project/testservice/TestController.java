package advancedweb.project.testservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final WebClient.Builder webClientBuilder;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/test-service")
    public String service1() {
        kafkaProducer.send("test-service", "test-service");
        return "서비스 1에서 완성되는 고유한 작업 요청 응답";
    }

    @GetMapping("/test-service/byService2")
    public Mono<String> byService2() {
        //요청 1개에 대해 결과가 0 or 1 만 나오게 구성
        return webClientBuilder.baseUrl("http://localhost:8082") // 서비스 2에 대한 특정 url 요청
                .build()
                .get()
                .uri("/service2")
                .retrieve()
                .bodyToMono(String.class)
                // lambda 식으로 처리
                .map(res -> "서비스 1의 요청에 대한 서비스 2의 응답: " + res);
    }
}
