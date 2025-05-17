package advancedweb.project.welfareservice.infra.repository;

import advancedweb.project.welfareservice.domain.entity.Welfare;
import advancedweb.project.welfareservice.domain.entity.enums.Area;
import advancedweb.project.welfareservice.domain.entity.enums.Target;

import java.util.List;

public interface WelfareRepositoryCustom {
    List<Welfare> findByAreaOrTargetIncludingEmpty(Area area, Target target);
}
