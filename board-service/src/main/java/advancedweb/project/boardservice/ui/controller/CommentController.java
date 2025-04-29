package advancedweb.project.boardservice.ui.controller;

import advancedweb.project.boardservice.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{postNo}")
public class CommentController {

    /**
     *  댓글 작성 API
     */
    public BaseResponse<Void> write() {
        return BaseResponse.onSuccess();
    }

    /**
     *  댓글 삭제 API
     */
    @DeleteMapping("/{commentNo}")
    public BaseResponse<Void> delete(@PathVariable String commentNo) {
        return BaseResponse.onSuccess();
    }
}
