package org.example.ratelimiter.interfaces;

import org.example.ratelimiter.UserRateLimiterConfig;
import org.example.ratelimiter.UserBucketRepository;

public interface RateLimiter {
    boolean allowRequest(String userId, UserBucketRepository repository, UserRateLimiterConfig config);
}
