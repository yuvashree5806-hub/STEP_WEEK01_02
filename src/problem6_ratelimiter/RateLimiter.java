package problem6_ratelimiter;

import java.util.HashMap;

public class RateLimiter {

    private HashMap<String, TokenBucket> clientBuckets = new HashMap<>();

    private final int MAX_REQUESTS = 1000;
    private final double REFILL_RATE = 1000.0 / 3600.0; // tokens per second

    public boolean checkRateLimit(String clientId) {

        clientBuckets.putIfAbsent(
                clientId,
                new TokenBucket(MAX_REQUESTS, REFILL_RATE)
        );

        TokenBucket bucket = clientBuckets.get(clientId);

        boolean allowed = bucket.allowRequest();

        if (allowed) {
            System.out.println(
                    "Allowed (" + bucket.getRemainingTokens() +
                            " requests remaining)"
            );
        } else {
            System.out.println(
                    "Denied (0 requests remaining)"
            );
        }

        return allowed;
    }
}