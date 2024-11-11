package org.example;

import org.example.ratelimiter.RateLimiterService;
import org.example.ratelimiter.UserRateLimiterConfig;
import org.example.ratelimiter.UserBucketRepository;
import org.example.ratelimiter.enums.RateLimiterType;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        UserBucketRepository userBucketRepository = new UserBucketRepository();

        UserRateLimiterConfig config1 = new UserRateLimiterConfig(4, 2, 2, 10000, RateLimiterType.BUCKET);
        UserRateLimiterConfig config2 = new UserRateLimiterConfig(4, 2, 2, 10000, RateLimiterType.FIXED_WINDOW);

        userBucketRepository.addUserConfig("user1", config1);
        userBucketRepository.addUserConfig("user2", config2);

        RateLimiterService rateLimiterService = new RateLimiterService(userBucketRepository);

        boolean attempt1 = rateLimiterService.allowRequest("user1");
        //Thread.sleep(2000);
        boolean attempt2 = rateLimiterService.allowRequest("user1");
        System.out.println(String.format("For userId1, the first attempt: %s, the second attempt: %s", attempt1, attempt2));
    }
}