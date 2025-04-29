package advancedweb.project.welfareservice.ui.controller;

import advancedweb.project.welfareservice.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatbot")
public class ChatBotController {

    /**
     * 챗봇에게 질문하기 API
     * 유저가 현재 확인하는 복지 서비스 내용들과 함께 챗봇에게 질문해야 하므로 Welfare에 위치
     */
    @GetMapping("/{welfareNo}")
    public BaseResponse<Void> question(@PathVariable String welfareNo) {
        return BaseResponse.onSuccess();
    }
}
