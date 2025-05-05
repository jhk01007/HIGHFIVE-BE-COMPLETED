package advancedweb.project.datacollector.application.dto.response.crawling;

import lombok.Builder;

@Builder
public record CrawlingResponse(
        String targetDetail,
        String criteria,    // 선정 기준
        String content,     // 서비스 내용
        String applyMethod,     // 신청 방법
        String tel,     // 전화 문의
        String referenceLink,    // 관련 웹사이트
        String reference    // 근거 법령 및 자료
) {}
