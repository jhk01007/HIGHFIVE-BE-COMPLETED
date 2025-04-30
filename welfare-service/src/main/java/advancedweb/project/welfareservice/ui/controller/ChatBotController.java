package advancedweb.project.welfareservice.ui.controller;

import advancedweb.project.welfareservice.application.dto.request.AskReq;
import advancedweb.project.welfareservice.application.usecase.AskChatbotUseCase;
import advancedweb.project.welfareservice.global.annotation.CheckAuthorization;
import advancedweb.project.welfareservice.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatbot")
public class ChatBotController {

    private final AskChatbotUseCase askChatbotUseCase;

    /**
     * 챗봇에게 질문하기 API
     * 유저가 현재 확인하는 복지 서비스 내용들과 함께 챗봇에게 질문해야 하므로 Welfare에 위치
     */
    @GetMapping("/{welfareNo}")
    @CheckAuthorization
    public BaseResponse<String> question(@PathVariable String welfareNo, @RequestBody AskReq request) {
        return BaseResponse.onSuccess(askChatbotUseCase.ask(welfareNo, request));
    }
}
