package org.example.ratelimiter.interfaces;

import org.example.ratelimiter.UserRateLimiterConfig;
import org.example.ratelimiter.RateLimiterManager;

public interface RateLimiter {
    boolean allowRequest(String userId, RateLimiterManager repository, UserRateLimiterConfig config);
}
