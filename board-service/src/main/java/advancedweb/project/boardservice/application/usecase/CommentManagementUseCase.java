package advancedweb.project.boardservice.application.usecase;

import advancedweb.project.boardservice.application.dto.request.WriteCmtReq;
import advancedweb.project.boardservice.application.dto.response.CommentRes;
import advancedweb.project.boardservice.domain.service.CommentService;
import advancedweb.project.boardservice.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static advancedweb.project.boardservice.global.exception.code.status.GlobalErrorStatus._NOT_COMMENT_OWNER;

@Service
@RequiredArgsConstructor
public class CommentManagementUseCase {

    // DI
    private final CommentService commentService;

    // Method
    @Transactional
    public void write(String postNo, WriteCmtReq request, String userNo) {
        commentService.save(postNo, request, userNo);
    }

    @Transactional(readOnly = true)
    public List<CommentRes> read(String postNo, String userNo) {
        return commentService.read(postNo).stream()
                .map(comment -> CommentRes.create(comment, userNo))
                .toList();
    }

    @Transactional
    public void delete(String commentNo, String userNo) {
        if (commentService.isMine(commentNo, userNo))
            throw new RestApiException(_NOT_COMMENT_OWNER);

        commentService.delete(commentNo);
    }
}
