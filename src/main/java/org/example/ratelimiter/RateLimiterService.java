package org.example.ratelimiter;

public class RateLimiterService {

    private final RateLimiterManager repository;

    public RateLimiterService(RateLimiterManager repository) {
        this.repository = repository;
    }

    public boolean allowRequest(String userId) {
        UserRateLimiterConfig config = repository.getUserConfig(userId);
        return config.getRateLimiter().allowRequest(userId, repository, config);
    }
}
