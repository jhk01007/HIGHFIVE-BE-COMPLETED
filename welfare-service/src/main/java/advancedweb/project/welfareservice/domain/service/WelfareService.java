package advancedweb.project.welfareservice.domain.service;

import advancedweb.project.welfareservice.domain.entity.Welfare;
import advancedweb.project.welfareservice.domain.entity.enums.Area;
import advancedweb.project.welfareservice.domain.entity.enums.Target;
import advancedweb.project.welfareservice.domain.repository.WelfareRepository;
import advancedweb.project.welfareservice.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static advancedweb.project.welfareservice.global.exception.code.status.GlobalErrorStatus._NOT_FOUND;

@Service
@RequiredArgsConstructor
public class WelfareService {

    private final WelfareRepository welfareRepository;

    public List<Welfare> filter(Area area, Target target) {
        return welfareRepository.findByAreaOrTargetIncludingEmpty(area, target);
    }

    public Welfare read(String welfareNo) {
        return welfareRepository.findById(welfareNo)
                .orElseThrow(() -> new RestApiException(_NOT_FOUND));
    }
}
