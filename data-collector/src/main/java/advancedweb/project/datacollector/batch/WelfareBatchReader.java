package advancedweb.project.datacollector.batch;

import advancedweb.project.datacollector.application.dto.response.api.WelfareApiResponse;
import advancedweb.project.datacollector.application.dto.response.api.WelfareItem;
import advancedweb.project.datacollector.domain.service.WelfareCrawlingService;
import advancedweb.project.datacollector.domain.service.WelfareDataFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WelfareBatchReader implements ItemReader<WelfareItem> {

    private final WelfareDataFetchService welfareDataFetchService;
    private Iterator<WelfareItem> iterator;

    @Override
    public WelfareItem read() {
        if (iterator == null) {
            WelfareApiResponse apiResponse = welfareDataFetchService.pull(1, 1000);

            List<WelfareItem> items = apiResponse.dataGroups().stream()
                    .filter(group -> group.row() != null)
                    .flatMap(group -> group.row().stream())
                    .toList();

            iterator = items.iterator();
        }

        return iterator.hasNext() ? iterator.next() : null;
    }

}
