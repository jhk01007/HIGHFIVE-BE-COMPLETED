package advancedweb.project.datacollector.domain.service;

import advancedweb.project.datacollector.application.dto.response.api.WelfareApiResponse;
import advancedweb.project.datacollector.infra.client.WelfarePublicApiFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WelfareDataFetchService {

    private final WelfarePublicApiFeignClient welfarePublicApiFeignClient;
    private final ObjectMapper objectMapper;

    @Value("${public-api.key}")
    private String key;
    private final static String type = "json";

    public WelfareApiResponse pull(int page, int size) {
        try {
            String jsonString = welfarePublicApiFeignClient.fetch(key, type, page, size);
            return objectMapper.readValue(jsonString, WelfareApiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
