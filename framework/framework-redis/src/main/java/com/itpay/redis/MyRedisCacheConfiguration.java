package com.itpay.redis;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * RedisCacheConfiguration 配置类
 */
public class MyRedisCacheConfiguration  {

    private Duration ttl;
    private boolean cacheNullValues;
    private String keyPrefix;
    private boolean usePrefix;

    private RedisSerializationContext.SerializationPair<String> keySerializationPair;
    private RedisSerializationContext.SerializationPair<Object> valueSerializationPair;

    private ConversionService conversionService;




}
