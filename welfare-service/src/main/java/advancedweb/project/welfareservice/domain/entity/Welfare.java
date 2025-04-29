package advancedweb.project.welfareservice.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Welfare {

    @Id
    private String welfareNo;   // PK
    private String name;        // 복지 서비스명
    private String detail;      // 복지 서비스 설명
    private String fileNo;      // 복지 서비스 파일 PK
    private WelfareType welfareType; // 복지 서비스 종류
    private Integer readCnt;    // 조회 수
    // todo: 복지 서비스 메타데이터 조사
}
