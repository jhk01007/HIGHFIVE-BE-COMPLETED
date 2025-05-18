package advancedweb.project.welfareservice.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ViewCountService {
    // Sorted Set 키
    private static final String ZSET_KEY = "WELFARE:VIEW_COUNT_ZSET";
    private final StringRedisTemplate redisTemplate;

    /**
     * welfareNo에 대한 뷰 카운트를 1 증가시킵니다.
     * ZSET을 이용해 welfareNo를 score로 관리합니다.
     */
    public void increment(String welfareNo) {
        redisTemplate.opsForZSet()
                .incrementScore(ZSET_KEY, welfareNo, 1);
    }

    /**
     * 조회수가 가장 높은 상위 5개의 welfareNo를 반환합니다.
     * score 내림차순(높은 순)으로 0~4위까지 가져옵니다.
     */
    public List<String> readTop5() {
        Set<String> topSet = redisTemplate.opsForZSet()
                .reverseRange(ZSET_KEY, 0, 4);
        if (topSet == null || topSet.isEmpty()) {
            return List.of();
        }
        return new ArrayList<>(topSet);
    }
}
