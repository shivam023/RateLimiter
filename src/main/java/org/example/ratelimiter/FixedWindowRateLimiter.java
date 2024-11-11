package org.example.ratelimiter;

import org.example.ratelimiter.interfaces.RateLimiter;

public class FixedWindowRateLimiter implements RateLimiter {
    @Override
    public boolean allowRequest(String userId, RateLimiterManager repository, UserRateLimiterConfig config) {
        return true;
    }
}
