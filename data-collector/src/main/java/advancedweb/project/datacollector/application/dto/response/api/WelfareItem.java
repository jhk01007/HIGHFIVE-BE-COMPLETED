package advancedweb.project.datacollector.application.dto.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WelfareItem (

        @JsonProperty("SIGUN_NM")
        String sigunName,
        @JsonProperty("SERVC_NM")
        String serviceName,
        @JsonProperty("SPORT_TARGET")
        String supportTarget,
        @JsonProperty("SERVC_RINK_ADDR")
        String serviceLink,
        @JsonProperty("CHARGE_DEPT_NM")
        String chargeDepartment,
        @JsonProperty("APLCATN_METH")
        String applicationMethod,
        @JsonProperty("SPORT_CYCL")
        String supportCycle
) {}