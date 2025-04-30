package advancedweb.project.userservice.application.dto.request;

public record ProfileReq(
        Integer age,    // 나이
        String area     // 지역 (시, 군, 구)
) {}
