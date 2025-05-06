package advancedweb.project.boardservice.application.usecase;

import advancedweb.project.boardservice.application.dto.request.WritePostReq;
import advancedweb.project.boardservice.application.dto.response.CommentRes;
import advancedweb.project.boardservice.application.dto.response.PostDetailRes;
import advancedweb.project.boardservice.application.dto.response.PostSummaryRes;
import advancedweb.project.boardservice.domain.entity.Post;
import advancedweb.project.boardservice.domain.service.CommentService;
import advancedweb.project.boardservice.domain.service.PostService;
import advancedweb.project.boardservice.global.exception.RestApiException;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static advancedweb.project.boardservice.global.exception.code.status.GlobalErrorStatus._NOT_POST_OWNER;

@Service
@RequiredArgsConstructor
public class PostManagementUseCase {

    // DI
    private final PostService postService;
    private final CommentManagementUseCase commentManagementUseCase;

    // Method
    @Transactional(readOnly = true)
    public Page<PostSummaryRes> readAll(Integer page) {
        return postService.readAll(
                PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"))
        );
    }

    @Transactional(readOnly = true)
    public List<PostSummaryRes> readRecentPost() {
        return null;
    }

    @Transactional
    public PostDetailRes read(String postNo, String userNo) {
        Post post = postService.read(postNo);
        List<CommentRes> comments = commentManagementUseCase.read(postNo, userNo);
        // 캐싱 서버로 해당 데이터 전송
        return PostDetailRes.create(post, comments, userNo);
    }

    @Transactional
    public void write(String userNo, WritePostReq request) {
        postService.save(userNo, request);
    }

    @Transactional
    public void delete(String postNo, String userNo) {
        if (!postService.isMine(postNo, userNo))
            throw new RestApiException(_NOT_POST_OWNER);

        postService.delete(postNo);
    }
}
