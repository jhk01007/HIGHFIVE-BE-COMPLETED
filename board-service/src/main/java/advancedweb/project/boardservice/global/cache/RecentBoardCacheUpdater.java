package advancedweb.project.boardservice.global.cache;

import advancedweb.project.boardservice.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecentBoardCacheUpdater {

    private static final String RECENT_POST_KEY = "BOARDS:RECENT";
    private final RedisTemplate<String, Post> redisTemplate;

    public void cacheRecentPost(Post post) {
        redisTemplate.opsForList().remove(RECENT_POST_KEY, 0, post);
        redisTemplate.opsForList().leftPush(RECENT_POST_KEY, post);
        redisTemplate.opsForList().trim(RECENT_POST_KEY, 0, 4);
    }

    public List<Post> readRecentPosts() {
        return redisTemplate.opsForList().range(RECENT_POST_KEY, 0, 4);
    }
}
