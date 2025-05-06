package advancedweb.project.boardservice.domain.service;

import advancedweb.project.boardservice.application.dto.request.WriteCmtReq;
import advancedweb.project.boardservice.domain.entity.Comment;
import advancedweb.project.boardservice.domain.repository.CommentRepository;
import advancedweb.project.boardservice.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static advancedweb.project.boardservice.global.exception.code.status.GlobalErrorStatus._NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(String postNo, WriteCmtReq request, String userNo) {
        commentRepository.save(
                Comment.create(request.content(), userNo, postNo)
        );
    }

    public List<Comment> read(String postNo) {
        return commentRepository.findByPostNo(postNo);
    }

    public boolean isMine(String commentNo, String userNo) {
        Comment comment = commentRepository.findById(commentNo)
                .orElseThrow(() -> new RestApiException(_NOT_FOUND));

        return comment.getWriterNo().equals(userNo);
    }

    public void delete(String commentNo) {
        commentRepository.deleteById(commentNo);
    }
}
