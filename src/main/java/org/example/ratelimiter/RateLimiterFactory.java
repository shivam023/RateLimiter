package org.example.ratelimiter;

import org.example.ratelimiter.enums.RateLimiterType;
import org.example.ratelimiter.interfaces.RateLimiter;

public class RateLimiterFactory {
    public static RateLimiter getRateLimiter(RateLimiterType rateLimiterType) {
        switch (rateLimiterType) {
            case FIXED_WINDOW:
                return new FixedWindowRateLimiter();
            case BUCKET:
                return new TokenBucketRateLimiter();
            default:
                throw new IllegalArgumentException("Unknown RateLimiter type");
        }
    }
}
