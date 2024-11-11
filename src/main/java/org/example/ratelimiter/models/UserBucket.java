package org.example.ratelimiter.models;

import org.example.ratelimiter.UserRateLimiterConfig;

import java.util.concurrent.atomic.AtomicInteger;

public class UserBucket {

    private final String userId;
    private int availableTokens;
    private long lastRefillTimestamp;

    public UserBucket(String userId, int initialTokens) {
        this.userId = userId;
        this.lastRefillTimestamp = System.currentTimeMillis();
        this.availableTokens = initialTokens;
    }

    public int getAvailableTokens() {
        return availableTokens;
    }

    public void setAvailableTokens(int availableTokens) {
        this.availableTokens = availableTokens;
    }

    public String getUserId() {
        return userId;
    }

    public long getLastRefillTimestamp() {
        return lastRefillTimestamp;
    }

    public void setLastRefillTimestamp(long lastRefillTimestamp) {
        this.lastRefillTimestamp = lastRefillTimestamp;
    }
}
