package advancedweb.project.welfareservice.application.dto.request;

import advancedweb.project.welfareservice.domain.entity.Welfare;

public record RecommendWelfareItemReq(
        String pk,
        String supportTarget,
        String welfareTitle,
        String selectionCriteria,
        String applyMethod
) {
    public static RecommendWelfareItemReq create(Welfare welfare) {
        return new RecommendWelfareItemReq(
                welfare.getWelfareNo(),
                welfare.getDetail().getTarget(),
                welfare.getDetail().getCriteria(),
                welfare.getDetail().getContent(),
                welfare.getDetail().getApplyMethod()
        );
    }
}
