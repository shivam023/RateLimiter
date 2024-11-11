package org.example.ratelimiter;

import org.example.ratelimiter.interfaces.RateLimiter;
import org.example.ratelimiter.models.UserBucket;

public class FixedWindowRateLimiter implements RateLimiter {
    @Override
    public boolean allowRequest(String userId, UserBucketRepository repository, UserRateLimiterConfig config) {
        return true;
    }
}
