package advancedweb.project.welfareservice.application.usecase;

import advancedweb.project.welfareservice.application.dto.request.RecommendReq;
import advancedweb.project.welfareservice.application.dto.request.RecommendWelfareItemReq;
import advancedweb.project.welfareservice.application.dto.response.WelfareDetailRes;
import advancedweb.project.welfareservice.application.dto.response.WelfareSummaryRes;
import advancedweb.project.welfareservice.domain.entity.Welfare;
import advancedweb.project.welfareservice.domain.entity.enums.Area;
import advancedweb.project.welfareservice.domain.entity.enums.Target;
import advancedweb.project.welfareservice.domain.service.ViewCountService;
import advancedweb.project.welfareservice.domain.service.WelfareService;
import advancedweb.project.welfareservice.infra.client.AiFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WelfareManagementUseCase {

    // DI
    private final WelfareService welfareService;
    private final AiFeignClient aiFeignClient;
    private final ViewCountService viewCountService;

    // Method
    public List<WelfareSummaryRes> search(Area area, Target target, String question) {
        List<Welfare> filtered = welfareService.filter(area, target);

        List<String> recommendedIds = aiFeignClient
                .recommend(
                        RecommendReq.create(
                                question,
                                filtered.stream()
                                        .map(RecommendWelfareItemReq::create)
                                        .toList()
                        )
                )
                .recommendedPks();

        return filtered.stream()
                .filter(w -> recommendedIds.contains(w.getWelfareNo()))
                .map(WelfareSummaryRes::create)
                .toList();
    }


    public WelfareDetailRes read(String welfareNo) {
        Welfare welfare = welfareService.read(welfareNo);
        viewCountService.increment(welfareNo);
        return WelfareDetailRes.create(welfare);
    }

    public List<WelfareSummaryRes> readPopularWelfare() {
        return viewCountService.readTop5().stream()
                .map(welfareService::read)
                .map(WelfareSummaryRes::create)
                .toList();
    }
}
