package advancedweb.project.boardservice.global.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import advancedweb.project.boardservice.domain.entity.Post;
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
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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

    /** Redis 연결 설정 (application.yml의 host/port 사용) */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration cfg = new RedisStandaloneConfiguration(host, port);
        return new LettuceConnectionFactory(cfg);
    }

    /**
     * Jackson에 JavaTimeModule과 Default Typing을 활성화한 Serializer 빈
     * → LocalDateTime 처리 + @class 타입정보 자동 포함
     */
    @Bean
    public GenericJackson2JsonRedisSerializer jacksonRedisSerializer() {
        ObjectMapper om = new ObjectMapper()
                // Java8 날짜/시간 지원
                .registerModule(new JavaTimeModule())
                // ISO-8601 문자열 포맷 사용 (타임스탬프 비활성화)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                // 모든 NON_FINAL 타입에 @class 힌트 포함
                .activateDefaultTyping(
                        LaissezFaireSubTypeValidator.instance,
                        ObjectMapper.DefaultTyping.NON_FINAL,
                        JsonTypeInfo.As.PROPERTY
                );
        return new GenericJackson2JsonRedisSerializer(om);
    }

    /** 캐시 매니저 설정 */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory cf,
                                     GenericJackson2JsonRedisSerializer serializer) {

        RedisCacheConfiguration defaultConf = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(serializer)
                );

        Map<String, RedisCacheConfiguration> configs = new HashMap<>();
        configs.put("auth",       defaultConf.entryTtl(Duration.ofMinutes(30)));
        configs.put("boards",     defaultConf);
        configs.put("boardsList", defaultConf);

        return RedisCacheManager.builder(cf)
                .cacheDefaults(defaultConf)
                .withInitialCacheConfigurations(configs)
                .build();
    }

    /** Post 엔티티 전용 RedisTemplate 설정 */
    @Bean
    public RedisTemplate<String, Post> postRedisTemplate(LettuceConnectionFactory cf,
                                                         GenericJackson2JsonRedisSerializer serializer) {
        RedisTemplate<String, Post> template = new RedisTemplate<>();
        template.setConnectionFactory(cf);

        // 키: 문자열 직렬화
        StringRedisSerializer strSer = new StringRedisSerializer();
        template.setKeySerializer(strSer);
        template.setHashKeySerializer(strSer);

        // 값: JSON 직렬화 (타입정보 포함)
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
