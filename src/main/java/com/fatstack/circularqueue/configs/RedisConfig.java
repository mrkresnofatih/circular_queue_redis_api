package com.fatstack.circularqueue.configs;

import com.fatstack.circularqueue.models.Chatroom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public JedisConnectionFactory getJedisConnFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration("localhost", 8001);
        return new JedisConnectionFactory(redisConfig);
    }

    @Bean
    public RedisTemplate<String, Chatroom> redisTemplate() {
        RedisTemplate<String, Chatroom> template = new RedisTemplate<>();
        template.setConnectionFactory(getJedisConnFactory());
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
