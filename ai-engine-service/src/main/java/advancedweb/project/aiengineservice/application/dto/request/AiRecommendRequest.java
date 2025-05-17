package advancedweb.project.aiengineservice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * Welfare-Service → AI-Service 로 넘어오는 추천 요청
 */
@ToString
public class AiRecommendRequest {
    private String rawUserInput;
    private List<WelfareItem> welfareItemList;
}

