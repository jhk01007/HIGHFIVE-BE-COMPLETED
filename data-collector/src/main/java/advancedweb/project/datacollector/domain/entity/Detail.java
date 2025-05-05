package advancedweb.project.datacollector.domain.entity;

import lombok.Builder;

@Builder
public class Detail {

    private String target;
    private String criteria;
    private String content;
    private String applyMethod;
    private String tel;
    private String referenceLink;
    private String reference;
}
