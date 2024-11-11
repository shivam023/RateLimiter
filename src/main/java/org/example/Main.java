package org.example;

import org.example.ratelimiter.RateLimiterService;
import org.example.ratelimiter.UserRateLimiterConfig;
import org.example.ratelimiter.RateLimiterManager;
import org.example.ratelimiter.enums.RateLimiterType;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterManager userBucketRepository = new RateLimiterManager();

        UserRateLimiterConfig config1 = new UserRateLimiterConfig(4, 1, 2, 2000, RateLimiterType.BUCKET);
        UserRateLimiterConfig config2 = new UserRateLimiterConfig(4, 1, 2, 2000, RateLimiterType.FIXED_WINDOW);

        userBucketRepository.addUserConfig("user1", config1);
        userBucketRepository.addUserConfig("user2", config2);

        RateLimiterService rateLimiterService = new RateLimiterService(userBucketRepository);

        boolean attempt1 = rateLimiterService.allowRequest("user1");
        System.out.println(String.format("For userId1, attempt1: %s", attempt1));
        Thread.sleep(3000);
        boolean attempt2 = rateLimiterService.allowRequest("user1");
        System.out.println(String.format("For userId1, attempt2: %s", attempt2));
        boolean attempt3 = rateLimiterService.allowRequest("user1");
        System.out.println(String.format("For userId1, attempt3: %s", attempt3));
        boolean attempt4 = rateLimiterService.allowRequest("user1");
        System.out.println(String.format("For userId1, attempt4: %s", attempt4));
        boolean attempt5 = rateLimiterService.allowRequest("user1");
        System.out.println(String.format("For userId1, attempt5: %s", attempt5));
    }
}