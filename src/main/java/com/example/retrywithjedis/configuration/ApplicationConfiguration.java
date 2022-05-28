package com.example.retrywithjedis.configuration;

import com.example.retrywithjedis.adapters.secondary.cache.RedisPaymentCache;
import com.example.retrywithjedis.domain.payment.PaymentCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class ApplicationConfiguration {

    private final JedisPool jedisPool;

    public ApplicationConfiguration(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Bean
    public PaymentCache paymentCache() {
        return new RedisPaymentCache(jedisPool);
    }
}
