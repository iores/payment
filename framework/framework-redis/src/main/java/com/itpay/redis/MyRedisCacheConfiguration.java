package com.itpay.redis;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * 自定义redis 配置信息
 *
 * @author zlf
 * @date 2017-12-27
 */
public class MyRedisCacheConfiguration {


    /**
     * @param ttl                  超时时间
     * @param cacheNullValues      是否缓存null false的话不缓存，那么value为null 则会报错
     * @param keyPrefix            key的前缀
     * @param usePrefix            是否使用 前缀
     * @param keySerializationPair key 的序列化程序
     * @param conversionService    key 的转换器
     * @return RedisCacheConfiguration
     */
    public static RedisCacheConfiguration createConfiguration(Long ttl, boolean cacheNullValues, String keyPrefix,
                                                              boolean usePrefix, SerializationPair<String> keySerializationPair,
                                                              SerializationPair<Object> valueSerializationPair, ConversionService conversionService) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        if (ttl != null) {
            configuration = configuration.entryTtl(Duration.ofSeconds(ttl));
        }
        if (!cacheNullValues) {
            configuration = configuration.disableCachingNullValues();
        }
        /*keyPrefix 设置值之后会修改usePrefix 为 true  因此需放前面*/
        if (StringUtils.hasText(keyPrefix)) {
            configuration = configuration.prefixKeysWith(keyPrefix);
        }
        if (!usePrefix) {
            configuration = configuration.disableKeyPrefix();
        }
        if (keySerializationPair != null) {
            configuration = configuration.serializeKeysWith(keySerializationPair);
        }
        if (valueSerializationPair != null) {
            configuration = configuration.serializeValuesWith(valueSerializationPair);
        }
        if (conversionService != null) {
            configuration = configuration.withConversionService(conversionService);
        }

        return configuration;

    }

    /**
     * @param ttl       超时时间
     * @param keyPrefix key的前缀
     * @return RedisCacheConfiguration
     */
    public static RedisCacheConfiguration createConfiguration(Long ttl, String keyPrefix, SerializationPair<Object> valueSerializationPair) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        if (ttl != null) {
            configuration = configuration.entryTtl(Duration.ofSeconds(ttl));
        }
        if (StringUtils.hasText(keyPrefix)) {
            configuration = configuration.prefixKeysWith(keyPrefix);
        }
        if (valueSerializationPair != null) {
            configuration = configuration.serializeValuesWith(valueSerializationPair);
        }
        return configuration;

    }

    /**
     * @param ttl       超时时间
     * @param keyPrefix key的前缀
     * @param usePrefix 是否使用 前缀
     * @return RedisCacheConfiguration
     */
    public static RedisCacheConfiguration createConfiguration(Long ttl, String keyPrefix, boolean usePrefix, SerializationPair<Object> valueSerializationPair) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        if (ttl != null) {
            configuration = configuration.entryTtl(Duration.ofSeconds(ttl));
        }
        /*keyPrefix 设置值之后会修改usePrefix 为 true  因此需放前面*/
        if (StringUtils.hasText(keyPrefix)) {
            configuration = configuration.prefixKeysWith(keyPrefix);
        }
        if (!usePrefix) {
            configuration = configuration.disableKeyPrefix();
        }

        if (valueSerializationPair != null) {
            configuration = configuration.serializeValuesWith(valueSerializationPair);
        }

        return configuration;

    }


}
