package org.example.ratelimiter;

import org.example.ratelimiter.models.UserBucket;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterManager {
    private final Map<String, UserBucket> userBuckets = new HashMap<>();

    private final Map<String, UserRateLimiterConfig> userConfigs = new HashMap<>();

    public RateLimiterManager() {
    }

    public UserRateLimiterConfig getUserConfig(String userId) {
        return userConfigs.get(userId);
    }

    public void addUserConfig(String userId, UserRateLimiterConfig rateLimiterConfig) {
        userConfigs.put(userId, rateLimiterConfig);
    }

    public UserBucket getUserBucket(String userId) {
        if(!userBuckets.containsKey(userId)) {
            userBuckets.put(userId, new UserBucket(userId, userConfigs.get(userId).getRefillAmount()));
        }

        return userBuckets.get(userId);
    }
}
