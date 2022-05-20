package com.example.retrywithjedis.domain.order;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("Order")
public final class Order implements Serializable {

    private final UUID id;

    public Order(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
