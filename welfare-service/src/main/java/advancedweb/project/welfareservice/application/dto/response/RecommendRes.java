package advancedweb.project.welfareservice.application.dto.response;

import java.util.List;

public record RecommendRes (
        List<String> recommendedPks
) {}
