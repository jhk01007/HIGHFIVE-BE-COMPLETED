package advancedweb.project.aiengineservice.application.usecase;

import advancedweb.project.aiengineservice.domain.service.GeminiService;
import advancedweb.project.aiengineservice.application.dto.request.AiRecommendRequest;
import advancedweb.project.aiengineservice.application.dto.response.AiRecommendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendWelfareUseCase {

    private final GeminiService geminiService;

    public AiRecommendResponse recommend(AiRecommendRequest request) {
        return new AiRecommendResponse(geminiService.requestRecommendation(request));
    }
}
