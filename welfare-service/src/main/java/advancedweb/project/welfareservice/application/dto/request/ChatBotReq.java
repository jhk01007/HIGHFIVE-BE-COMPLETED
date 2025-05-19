package advancedweb.project.welfareservice.application.dto.request;

import advancedweb.project.welfareservice.domain.entity.Welfare;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBotReq {
    private String currentWelfarePk;
    private String currentWelfareName;
    private String supportTarget;
    private String selectionCriteria;
    private String welfareContent;
    private String applicationMethod;
    private String userQuestion;

    public static ChatBotReq create(Welfare welfare, String question) {
        return new ChatBotReq(
                welfare.getWelfareNo(),
                welfare.getSummary().getName(),
                welfare.getDetail().getTarget(),
                welfare.getDetail().getCriteria(),
                welfare.getDetail().getContent(),
                welfare.getDetail().getApplyMethod(),
                question
        );
    }
}
