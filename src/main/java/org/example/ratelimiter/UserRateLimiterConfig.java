package org.example.ratelimiter;

import org.example.ratelimiter.enums.RateLimiterType;
import org.example.ratelimiter.interfaces.RateLimiter;

public class UserRateLimiterConfig {
    private int maxTokens;
    private final int refillAmount;
    private final int rewardAmount;
    private final long refillIntervalInMillis;
    private RateLimiter rateLimiter;

    private RateLimiterType rateLimiterType;

    public UserRateLimiterConfig(int maxTokens, int refillAmount, int rewardAmount, long refillIntervalInMillis, RateLimiterType rateLimiterType) {
        this.maxTokens = maxTokens;
        this.refillAmount = refillAmount;
        this.rewardAmount = rewardAmount;
        this.refillIntervalInMillis = refillIntervalInMillis;
        this.rateLimiter = RateLimiterFactory.getRateLimiter(rateLimiterType);
    }

    public long getRefillIntervalInMillis() {
        return refillIntervalInMillis;
    }

    public int getRewardAmount() {
        return rewardAmount;
    }

    public int getRefillAmount() {
        return refillAmount;
    }

    public RateLimiterType getRateLimiterType() {
        return rateLimiterType;
    }

    public void setRateLimiterType(RateLimiterType rateLimiterType) {
        this.rateLimiterType = rateLimiterType;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }
    public RateLimiter getRateLimiter() {
        return rateLimiter;
    }

    public void setRateLimiter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }
}
