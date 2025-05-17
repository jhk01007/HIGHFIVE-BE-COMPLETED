package advancedweb.project.welfareservice.application.usecase;

import advancedweb.project.welfareservice.application.dto.request.AskReq;
import advancedweb.project.welfareservice.domain.entity.Welfare;
import advancedweb.project.welfareservice.domain.service.WelfareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AskChatbotUseCase {

    // DI
    private final WelfareService welfareService;

    // Method
    public String ask(String welfareNo, AskReq request) {
        Welfare welfare = welfareService.read(welfareNo);
        

        return null;
    }
}
