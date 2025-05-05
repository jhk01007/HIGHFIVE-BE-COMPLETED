package advancedweb.project.datacollector.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "welfarePublicApiClient",
        url = "https://openapi.gg.go.kr/"  // ← 도메인까지만!
)
public interface WelfarePublicApiFeignClient {

    @GetMapping("TBWELFARESSRSM")  // ← 경로는 여기서 지정
    String fetch(
            @RequestParam("KEY") String key,
            @RequestParam("Type") String type,
            @RequestParam("pIndex") int pageIndex,
            @RequestParam("pSize") int pageSize
    );
}
