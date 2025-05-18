package advancedweb.project.boardservice.domain.service;

import advancedweb.project.boardservice.application.dto.request.WritePostReq;
import advancedweb.project.boardservice.application.dto.response.PostSummaryRes;
import advancedweb.project.boardservice.domain.entity.Post;
import advancedweb.project.boardservice.domain.repository.PostRepository;
import advancedweb.project.boardservice.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static advancedweb.project.boardservice.global.exception.code.status.GlobalErrorStatus._NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post save(String userNo, WritePostReq request) {
        return postRepository.save(
                Post.create(request.title(), request.content(), userNo)
        );
    }

    public Post read(String postNo) {
        return postRepository.findById(postNo)
                .orElseThrow(() -> new RestApiException(_NOT_FOUND));
    }

    public boolean isMine(String postNo, String userNo) {
        Post post = read(postNo);
        return post.getWriterNo().equals(userNo);
    }

    public void delete(String postNo) {
        postRepository.deleteById(postNo);
    }

    public Page<PostSummaryRes> readAll(PageRequest pageRequest) {
        List<PostSummaryRes> result = readAllToList(pageRequest);
        return new PageImpl<>(result, pageRequest, result.size());
    }

    @Cacheable(
            cacheNames = "boardsList",
            key = "'BOARD:LIST:' + #page"
    )
    public List<PostSummaryRes> readAllToList(PageRequest pageRequest) {
        return postRepository.findAll(pageRequest).stream()
                .map(PostSummaryRes::create)
                .toList();
    }
}
