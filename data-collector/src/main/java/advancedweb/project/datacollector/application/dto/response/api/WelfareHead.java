package advancedweb.project.datacollector.application.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WelfareHead (
        @JsonProperty("list_total_count")
        Integer listTotalCount,
        @JsonProperty("RESULT")
        Result result,
        @JsonProperty("api_version")
        String apiVersion
) {}