package advancedweb.project.boardservice.ui.controller;

import advancedweb.project.boardservice.global.annotation.CheckAuthorization;
import advancedweb.project.boardservice.global.annotation.CurrentUser;
import advancedweb.project.boardservice.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class PostController {
    /**
     *  게시물 상세 조회 API
     *  게시물 및 댓글 조회
     */
    @CheckAuthorization
    @GetMapping("/{postNo}")
    public BaseResponse<Void> read(@PathVariable String postNo, @CurrentUser String userNo) {
        System.out.println(userNo);
        return BaseResponse.onSuccess();
    }


    /**
     *  게시물 목록 조회 API
     *  게시물 썸네일 목록 조회
     */
    @GetMapping
    public BaseResponse<Void> readAll() {
        return BaseResponse.onSuccess();
    }

    /**
     *  게시물 작성 API
     */
    @PostMapping
    public BaseResponse<Void> write() {
        return BaseResponse.onSuccess();
    }

    /**
     *  게시물 삭제 API
     */
    @DeleteMapping
    public BaseResponse<Void> delete() {
        return BaseResponse.onSuccess();
    }
}
