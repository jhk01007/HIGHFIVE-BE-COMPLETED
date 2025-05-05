package advancedweb.project.welfareservice.domain.entity;

import advancedweb.project.welfareservice.domain.entity.enums.Area;
import advancedweb.project.welfareservice.domain.entity.enums.Target;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
public class Welfare {

    @Id
    private String welfareNo;   // PK

    // Api
    private String name;        // 복지 서비스명
    private Set<Area> areas;    // 지역
    private Set<Target> targets;    // 지원 대상

    // Crawling
    private String targetDetail;    // 지원 대상
    private String criteria;    // 선정 기준
    private String content;     // 서비스 내용
    private String applyMethod;     // 신청 방법
    private String tel;     // 전화 문의
    private String referenceLink;    // 관련 웹사이트

    // Metadata
    private Integer readCnt;    // 조회 수

    // File
    private String fileNo;      // 복지 서비스 파일 PK
}
