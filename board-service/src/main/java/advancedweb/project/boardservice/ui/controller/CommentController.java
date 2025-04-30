package advancedweb.project.boardservice.ui.controller;

import advancedweb.project.boardservice.application.dto.request.WriteCmtReq;
import advancedweb.project.boardservice.application.usecase.CommentManagementUseCase;
import advancedweb.project.boardservice.global.annotation.CheckAuthorization;
import advancedweb.project.boardservice.global.annotation.CurrentUser;
import advancedweb.project.boardservice.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{postNo}")
public class CommentController {

    private final CommentManagementUseCase commentManagementUseCase;

    /**
     *  댓글 작성 API
     */
    @PostMapping
    @CheckAuthorization
    public BaseResponse<Void> write(@PathVariable String postNo, @RequestBody WriteCmtReq request, @CurrentUser String userNo) {
        commentManagementUseCase.write(postNo, request, userNo);
        return BaseResponse.onSuccess();
    }

    /**
     *  댓글 삭제 API
     */
    @DeleteMapping("/{commentNo}")
    @CheckAuthorization
    public BaseResponse<Void> delete(@PathVariable String postNo, @PathVariable String commentNo, @CurrentUser String userNo) {
        commentManagementUseCase.delete(postNo, commentNo);
        return BaseResponse.onSuccess();
    }
}
