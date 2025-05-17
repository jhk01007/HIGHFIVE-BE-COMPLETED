package advancedweb.project.boardservice.application.usecase;

import advancedweb.project.boardservice.application.dto.request.WritePostReq;
import advancedweb.project.boardservice.application.dto.response.CommentRes;
import advancedweb.project.boardservice.application.dto.response.PostDetailRes;
import advancedweb.project.boardservice.application.dto.response.PostSummaryRes;
import advancedweb.project.boardservice.domain.entity.Post;
import advancedweb.project.boardservice.domain.service.PostService;
import advancedweb.project.boardservice.global.cache.RecentBoardCacheUpdater;
import advancedweb.project.boardservice.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    private final RecentBoardCacheUpdater recentBoardCacheUpdater;

    // Method
    @Transactional(readOnly = true)
    @Cacheable(
            cacheNames = "boardsList",
            key = "'BOARD:LIST:' + #page"
    )
    public Page<PostSummaryRes> readAll(Integer page) {
        return postService.readAll(
                PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"))
        );
    }

    @Transactional(readOnly = true)
    public List<PostSummaryRes> readRecentPost() {
        return recentBoardCacheUpdater.readRecentPosts().stream()
                .map(PostSummaryRes::create)
                .toList();
    }

    @Transactional
    @Cacheable(
            cacheNames = "boards",
            key = "'BOARD:' + #postNo"
    )
    public PostDetailRes read(String postNo, String userNo) {
        Post post = postService.read(postNo);
        List<CommentRes> comments = commentManagementUseCase.read(postNo, userNo);
        recentBoardCacheUpdater.cacheRecentPost(post);

        return PostDetailRes.create(post, comments, userNo);
    }

    @Transactional
    @CachePut(
            cacheNames = "boards",
            key = "'BOARD:' + #result.postNo"
    )
    @CacheEvict(
            cacheNames = "boardsList",
            allEntries = true
    )
    public Post write(String userNo, WritePostReq request) {
        Post post = postService.save(userNo, request);
        recentBoardCacheUpdater.cacheRecentPost(post);

        return post;
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(
                    cacheNames = "boards",
                    key        = "'BOARD:' + #postId"
            ),
            @CacheEvict(
                    cacheNames  = "boardsList",
                    allEntries  = true
            )
    })
    public void delete(String postNo, String userNo) {
        if (!postService.isMine(postNo, userNo))
            throw new RestApiException(_NOT_POST_OWNER);

        postService.delete(postNo);
    }
}
