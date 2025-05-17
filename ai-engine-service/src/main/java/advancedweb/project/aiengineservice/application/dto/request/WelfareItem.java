package advancedweb.project.aiengineservice.application.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WelfareItem {
    private String pk;
    private String supportTarget;
    private String welfareTitle;
    private String selectionCriteria;
    private String applyMethod;
}