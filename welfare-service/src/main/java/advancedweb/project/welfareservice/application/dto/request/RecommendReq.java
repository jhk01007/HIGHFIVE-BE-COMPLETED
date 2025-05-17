package advancedweb.project.welfareservice.application.dto.request;

import java.util.List;

public record RecommendReq (
        String rawUserInput,
        List<RecommendWelfareItemReq> welfareItemList
) {
    public static RecommendReq create(String question, List<RecommendWelfareItemReq> items) {
        return new RecommendReq(question, items);
    }
}
