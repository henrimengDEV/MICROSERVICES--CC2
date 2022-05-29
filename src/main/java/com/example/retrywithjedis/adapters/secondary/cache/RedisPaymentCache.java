package com.example.retrywithjedis.adapters.secondary.cache;

import com.example.retrywithjedis.domain.payment.Payment;
import com.example.retrywithjedis.domain.payment.PaymentCache;
import com.example.retrywithjedis.domain.payment.PaymentId;
import com.example.retrywithjedis.kernel.PaymentAlreadyInProcessException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.NoSuchElementException;
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
    public void verifyTransactionIdempotency(UUID UUID) throws PaymentAlreadyInProcessException{
        Jedis jedis = null;

        try {
            jedis = acquireJedisInstance();
            Boolean exist = jedis.exists(keyPrefix + UUID);
            if (exist) {
                String paymentJSON = jedis.get(keyPrefix + UUID);
                Payment payment = gson.fromJson(paymentJSON, Payment.class);
                throw PaymentAlreadyInProcessException.withTransactionId(payment.getUuid());
            }
        } catch (Exception e) {
            logger.info("Error occured while checking if data exist from the cache " + e.getMessage());
            releaseJedisInstance(jedis);
            throw new RuntimeException(e);
        } finally {
            releaseJedisInstance(jedis);
        }
    }

    @Override
    public void storeTransaction(UUID paymentUUID, String transactionStatus) {
        Jedis jedis = null;

        try {
            jedis = acquireJedisInstance();
            jedis.set(keyPrefix + paymentUUID, transactionStatus);
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
    public void storePayment(PaymentId paymentUUID, Payment payment) {
        Jedis jedis = null;

        try {
            jedis = acquireJedisInstance();
            String paymentJSON = gson.toJson(payment);
            jedis.set(keyPrefix + paymentUUID, paymentJSON);
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
    public String retrieveTransaction(UUID transactionId) {
        Jedis jedis = null;

        try {
            jedis = acquireJedisInstance();
            String transactionStatus = jedis.get(keyPrefix + transactionId);
            if (StringUtils.hasText(transactionStatus)) {
                return transactionStatus;
            }
        } catch (Exception e) {
            logger.info("Error occured while retrieving data from the cache " + e.getMessage());
            releaseJedisInstance(jedis);
            throw new RuntimeException(e);
        } finally {
            releaseJedisInstance(jedis);
        }

        throw new NoSuchElementException();
    }

    @Override
    public Payment retrievePayment(PaymentId paymentId) {
        //TODO
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
