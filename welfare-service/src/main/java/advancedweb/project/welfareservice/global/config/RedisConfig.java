package advancedweb.project.welfareservice.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.collections.RedisProperties;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory cf) {
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer())
                );

        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();

        cacheConfigs.put("auth", defaultConfig.entryTtl(Duration.ofMinutes(30)));
        cacheConfigs.put("boards", defaultConfig);
        cacheConfigs.put("boardsList", defaultConfig);

        return RedisCacheManager.builder(cf)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigs)
                .build();
    }

    @Bean
    public RedisTemplate<String, Integer> redisTemplate(LettuceConnectionFactory cf) {
        RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(cf);

        // 키는 문자열로 직렬화
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // 값은 Integer를 문자열로 변환해서 직렬화
        GenericToStringSerializer<Integer> intSerializer =
                new GenericToStringSerializer<>(Integer.class);
        template.setValueSerializer(intSerializer);
        template.setHashValueSerializer(intSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
