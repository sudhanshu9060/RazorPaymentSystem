package com.Sudhanshu.Razorpay.merchant.Cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.Optional;
@Component
@Slf4j
@RequiredArgsConstructor

public class RedisApiKeyCache implements ApiKeyCache {
    private final StringRedisTemplate  stringRedisTemplate;
    private static  final String Prefix= "apikey:";
    private final ObjectMapper objectMapper;
    private static  Duration TTL= Duration.ofMinutes(5);
    @Override
    public Optional<ApiKeyCacheEntry> get(String keyId) {
        try {
            String Json = stringRedisTemplate.opsForValue().get(Prefix + keyId);
            if(Json==null)
            {
                return Optional.empty();
            }
            return Optional.of(objectMapper.readValue(Json, ApiKeyCacheEntry.class));


        }
        catch (Exception e)
        {
            log.warn("Failed to get API key from Redis cache for keyId: {}. Error: {}", keyId, e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public void put(String keyId, ApiKeyCacheEntry entry) {
        try {
            stringRedisTemplate.opsForValue().set(Prefix+keyId,
                    objectMapper.writeValueAsString(entry),
                    TTL);
        } catch (Exception e) {
            log.warn("ApiKey cache put failed, keyId: {}", keyId);
        }

    }

    @Override
    public void evict(String keyId) {

            stringRedisTemplate.delete(Prefix+keyId);
        }


}
