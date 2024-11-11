package org.example.ratelimiter;

import org.example.ratelimiter.enums.RateLimiterType;
import org.example.ratelimiter.models.UserBucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TokenBucketRateLimiterTests {

    @InjectMocks
    private TokenBucketRateLimiter tokenBucketRateLimiter;

    @Spy
    private UserRateLimiterConfig rateLimiterConfig;

    private UserBucket userBucket;
    private RateLimiterManager rateLimiterManager;

    @BeforeEach
    void setup() {
        String userId = "123";
        rateLimiterManager = mock(RateLimiterManager.class);
        rateLimiterConfig = new UserRateLimiterConfig(10, 5, 1, 1000, RateLimiterType.BUCKET);
        userBucket = new UserBucket(userId, rateLimiterConfig.getRefillAmount());
        tokenBucketRateLimiter = new TokenBucketRateLimiter();
    }

    @Test
    public void allowRequests_WhenTokensAvailable() {
        when(rateLimiterManager.getUserBucket("123")).thenReturn(userBucket);
        when(rateLimiterManager.getUserConfig("123")).thenReturn(rateLimiterConfig);

        boolean result = tokenBucketRateLimiter.allowRequest("123", rateLimiterManager, rateLimiterConfig);
        assertTrue(result);
        assertEquals(4, userBucket.getAvailableTokens());
    }
}
