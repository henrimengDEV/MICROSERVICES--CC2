package com.example.retrywithjedis.adapters.secondary.cache;

import com.example.retrywithjedis.domain.payment.PaymentCache;
import com.example.retrywithjedis.domain.payment.PaymentStatus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class RedisPaymentCache implements PaymentCache {
    private final Logger logger = getLogger(String.valueOf(RedisPaymentCache.class));
    private final JedisPool jedisPool;
    private final Gson gson = new Gson();
    @Value("${redis.sessiondata.ttl}")
    private int sessiondataTTL;
    @Value("${redis.key-prefix.payment}")
    private String keyPrefix;

    public RedisPaymentCache(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    private Jedis acquireJedisInstance() {
        return jedisPool.getResource();
    }

    private void releaseJedisInstance(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
            jedis = null;
        }
    }

    @Override
    public void verifyIdempotency(UUID UUID) {
        Jedis jedis = null;

        try {
            jedis = acquireJedisInstance();
            String paymentJSON = jedis.get(keyPrefix + UUID);
            if (StringUtils.hasText(paymentJSON)) {
                throw new JedisDataException("Transaction already taken in count, status : " + paymentJSON);
            }
        } catch (Exception e) {
            logger.info("Error occured while retrieving data from the cache " + e.getMessage());
            releaseJedisInstance(jedis);
            throw new RuntimeException(e);
        } finally {
            releaseJedisInstance(jedis);
        }
    }

    @Override
    public void storePayment(UUID paymentUUID, String paymentStatus) {
        Jedis jedis = null;

        try {
            jedis = acquireJedisInstance();
            String json = gson.toJson(paymentStatus);
            jedis.set(keyPrefix + paymentUUID, json);
            jedis.expire(keyPrefix + paymentUUID, sessiondataTTL);
        } catch (Exception e) {
            logger.info("Error occured while storing data to the cache " + e.getMessage());
            releaseJedisInstance(jedis);
            throw new RuntimeException(e);
        } finally {
            releaseJedisInstance(jedis);
        }
    }

    @Override
    public PaymentStatus retrievePayment(UUID paymentUUID) {
        Jedis jedis = null;

        try {
            jedis = acquireJedisInstance();
            String paymentJSON = jedis.get(keyPrefix + paymentUUID);
            if (StringUtils.hasText(paymentJSON)) {
                return gson.fromJson(paymentJSON, PaymentStatus.class);
            }
        } catch (Exception e) {
            logger.info("Error occured while retrieving data from the cache " + e.getMessage());
            releaseJedisInstance(jedis);
            throw new RuntimeException(e);
        } finally {
            releaseJedisInstance(jedis);
        }

        return null;
    }

    @Override
    public void flushPaymentCache(UUID paymentUUID) {
        Jedis jedis = null;

        try {
            jedis = acquireJedisInstance();
            List<String> keys = jedis.lrange(keyPrefix + paymentUUID, 0, -1);
            if (!CollectionUtils.isEmpty(keys)) {
                // add the list key in as well
                keys.add(keyPrefix + paymentUUID);
                // delete the keys and list
                jedis.del(keys.toArray(new String[keys.size()]));
            }
        } catch (Exception e) {
            logger.info("Error occured while flushing specific data from the cache " + e.getMessage());
            releaseJedisInstance(jedis);
            throw new RuntimeException(e);
        } finally {
            releaseJedisInstance(jedis);
        }
    }

    @Override
    public void clearAll() {
        Jedis jedis = null;

        try {
            jedis = acquireJedisInstance();
            List<String> keys = jedis.lrange(keyPrefix, 0, -1);
            if (!CollectionUtils.isEmpty(keys)) {
                // add the list key in as well
                keys.add(keyPrefix);
                // delete the keys and list
                jedis.del(keys.toArray(new String[keys.size()]));
            }
        } catch (Exception e) {
            logger.info("Error occured while flushing all data from the cache " + e.getMessage());
            releaseJedisInstance(jedis);
            throw new RuntimeException(e);
        } finally {
            releaseJedisInstance(jedis);
        }
    }
}
