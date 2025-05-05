package advancedweb.project.datacollector.application.dto.response.api;

import java.util.List;

public record WelfareDataGroup (
        List<WelfareHead> head,
        List<WelfareItem> row
) {}
