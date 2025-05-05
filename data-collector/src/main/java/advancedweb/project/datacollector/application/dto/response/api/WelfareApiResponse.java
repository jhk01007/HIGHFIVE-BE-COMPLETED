package advancedweb.project.datacollector.application.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WelfareApiResponse (
        @JsonProperty("TBWELFARESSRSM")
        List<WelfareDataGroup> dataGroups

) {}




