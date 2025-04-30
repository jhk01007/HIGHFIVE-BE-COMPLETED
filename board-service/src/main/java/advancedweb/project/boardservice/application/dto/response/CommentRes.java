package advancedweb.project.boardservice.application.dto.response;

import java.time.LocalDateTime;

public record CommentRes (
        String content,     // 댓글 내용
        LocalDateTime createdAt     // 댓글 작성일
) {}
