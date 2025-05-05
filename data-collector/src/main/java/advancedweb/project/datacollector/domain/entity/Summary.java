package advancedweb.project.datacollector.domain.entity;

import advancedweb.project.datacollector.domain.entity.enums.Area;
import advancedweb.project.datacollector.domain.entity.enums.Target;
import lombok.Builder;

import java.util.Set;

@Builder
public class Summary {

    private String name;
    private Set<Area> areas;    // 지역
    private Set<Target> targets;    // 지원 대상
}
