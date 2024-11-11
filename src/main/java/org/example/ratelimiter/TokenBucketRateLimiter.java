package org.example.ratelimiter;

import org.example.ratelimiter.models.UserBucket;
import org.example.ratelimiter.interfaces.RateLimiter;

public class TokenBucketRateLimiter implements RateLimiter {
    @Override
    public boolean allowRequest(String userId, RateLimiterManager repository, UserRateLimiterConfig config) {
        UserRateLimiterConfig rateLimiterConfig = repository.getUserConfig(userId);
        UserBucket userBucket = repository.getUserBucket(userId);
        refillTokensIfNeeded(rateLimiterConfig, userBucket);

        if(userBucket.getAvailableTokens() > 0) {
            userBucket.setAvailableTokens(userBucket.getAvailableTokens() - 1);
            return true;
        }

        return false;
    }

    private void refillTokensIfNeeded(UserRateLimiterConfig rateLimiterConfig, UserBucket userBucket) {
        long currentTime = System.currentTimeMillis();
        long timeSinceLastRefill = currentTime - userBucket.getLastRefillTimestamp();

        if(timeSinceLastRefill >= rateLimiterConfig.getRefillIntervalInMillis()) {
            long refillCycles = timeSinceLastRefill / rateLimiterConfig.getRefillIntervalInMillis();
            int tokensToAdd = (int) refillCycles * rateLimiterConfig.getRefillAmount();
            if(userBucket.getAvailableTokens() < rateLimiterConfig.getMaxTokens()) {
                System.out.println(String.format("Adding reward amount %s for user %s", rateLimiterConfig.getRewardAmount(), userBucket.getUserId()));
                tokensToAdd += rateLimiterConfig.getRewardAmount();
            }

            int newTokenCount = Math.min(userBucket.getAvailableTokens() + tokensToAdd, rateLimiterConfig.getMaxTokens());
            userBucket.setAvailableTokens(newTokenCount);
            userBucket.setLastRefillTimestamp(currentTime);
        }
    }
}
