package advancedweb.project.datacollector.batch;

import advancedweb.project.datacollector.application.dto.response.api.WelfareItem;
import advancedweb.project.datacollector.application.dto.response.crawling.CrawlingResponse;
import advancedweb.project.datacollector.domain.entity.Detail;
import advancedweb.project.datacollector.domain.entity.Summary;
import advancedweb.project.datacollector.domain.entity.Welfare;
import advancedweb.project.datacollector.domain.entity.enums.Area;
import advancedweb.project.datacollector.domain.entity.enums.Target;
import advancedweb.project.datacollector.domain.service.WelfareCrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WelfareBatchProcessor implements ItemProcessor<WelfareItem, Welfare> {

    private final WelfareCrawlingService welfareCrawlingService;

    @Override
    public Welfare process(WelfareItem item) throws Exception {
        CrawlingResponse crawling = welfareCrawlingService.crawl(item.serviceLink());

        Set<Target> targets = item.supportTarget() != null ?
                Set.of(item.supportTarget().split(",\\s*")).stream()
                        .map(Target::from)
                        .collect(Collectors.toSet()) :
                Set.of();

        Set<Area> areas = (item.sigunName() != null && !item.sigunName().isBlank() && !item.sigunName().equals("-"))
                ? Set.of(Area.from(item.sigunName()))
                : Set.of();

        return Welfare.builder()
                .summary(
                        Summary.builder()
                                .name(item.serviceName())
                                .areas(areas)
                                .targets(targets)
                                .build()
                )
                .detail(
                        Detail.builder()
                                .target(crawling.targetDetail() != null ? crawling.targetDetail() : null)
                                .criteria(crawling.criteria() != null ? crawling.criteria() : null)
                                .content(crawling.content() != null ? crawling.content() : null)
                                .applyMethod(crawling.applyMethod() != null ? crawling.applyMethod() : null)
                                .tel(crawling.tel() != null ? crawling.tel() : null)
                                .referenceLink(crawling.referenceLink() != null ? crawling.referenceLink() : null)
                                .reference(crawling.reference() != null ? crawling.reference() : null)
                                .build()
                )
                .readCnt(0)
                .build();
    }
}
