package advancedweb.project.welfareservice.infra.client;

import advancedweb.project.welfareservice.application.dto.request.RecommendReq;
import advancedweb.project.welfareservice.application.dto.response.RecommendRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "welfare-service")
public interface RecommendFeignClient {

    @GetMapping
    List<RecommendRes> recommend(List<RecommendReq> request, String question);
}
