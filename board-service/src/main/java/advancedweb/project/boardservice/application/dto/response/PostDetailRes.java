package advancedweb.project.boardservice.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailRes(
        String title,   // 게시물 제목
        String content, // 게시물 내용
        LocalDateTime createdAt,    // 작성일
        List<CommentRes> comments   // 댓글 목록
) {}
