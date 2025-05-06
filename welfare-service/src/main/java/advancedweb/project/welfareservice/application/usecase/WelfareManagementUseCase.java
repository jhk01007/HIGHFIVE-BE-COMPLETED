package advancedweb.project.welfareservice.application.usecase;

import advancedweb.project.welfareservice.application.dto.request.RecommendReq;
import advancedweb.project.welfareservice.application.dto.response.RecommendRes;
import advancedweb.project.welfareservice.application.dto.response.WelfareDetailRes;
import advancedweb.project.welfareservice.application.dto.response.WelfareSummaryRes;
import advancedweb.project.welfareservice.domain.entity.Welfare;
import advancedweb.project.welfareservice.domain.entity.enums.Area;
import advancedweb.project.welfareservice.domain.entity.enums.Target;
import advancedweb.project.welfareservice.domain.service.WelfareService;
import advancedweb.project.welfareservice.infra.client.RecommendFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WelfareManagementUseCase {

    // DI
    private final WelfareService welfareService;
    private final RecommendFeignClient recommendFeignClient;

    // Method
    public List<WelfareSummaryRes> search(Area area, Target target, String question) {
        List<Welfare> filtered = welfareService.filter(area, target);

        List<String> recommendedIds = recommendFeignClient.recommend(filtered.stream()
                        .map(RecommendReq::create)
                        .toList(),
                question
        ).stream().map(RecommendRes::welfareNo).toList();

        return filtered.stream()
                .filter(item -> recommendedIds.contains(item.getWelfareNo()))
                .map(WelfareSummaryRes::create)
                .toList();
    }

    public WelfareDetailRes read(String welfareNo) {
        Welfare welfare = welfareService.read(welfareNo);
        return WelfareDetailRes.create(welfare);
    }
}
