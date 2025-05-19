package advancedweb.project.welfareservice.infra.client;

import advancedweb.project.welfareservice.application.dto.request.ChatBotReq;
import advancedweb.project.welfareservice.application.dto.request.RecommendReq;
import advancedweb.project.welfareservice.application.dto.response.ChatBotRes;
import advancedweb.project.welfareservice.application.dto.response.RecommendRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ai-engine-service")
public interface AiFeignClient {

    @PostMapping("/api/ai/recommend")
    RecommendRes recommend(RecommendReq request);

    @PostMapping("/api/ai/chat")
    ChatBotRes chat(ChatBotReq request);
}
