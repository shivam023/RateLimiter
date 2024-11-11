package org.example.ratelimiter;

public class RateLimiterService {

    private final UserBucketRepository repository;

    public RateLimiterService(UserBucketRepository repository) {
        this.repository = repository;
    }

    public boolean allowRequest(String userId) {
        UserRateLimiterConfig config = repository.getUserConfig(userId);
        return config.getRateLimiter().allowRequest(userId, repository, config);
    }
}
