package com.wls.redis_demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @Author:wangpeng
 * @Date: 2021/8/22
 * @Description: ***
 * @version:1.0
 */
@Configuration
public class MyTemplate {

    @Bean
    public StringRedisTemplate ooxx(RedisConnectionFactory fc) {

        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(fc);
        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return  stringRedisTemplate;

    }
}
