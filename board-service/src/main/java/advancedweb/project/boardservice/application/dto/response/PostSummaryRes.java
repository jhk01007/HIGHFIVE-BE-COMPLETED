package advancedweb.project.boardservice.application.dto.response;

import java.time.LocalDateTime;

public record PostSummaryRes(
        String title,
        LocalDateTime createdAt
) {}
