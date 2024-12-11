package com.selenium.failsafe.policies;

import dev.failsafe.RetryPolicy;

import java.time.Duration;

import static com.selenium.failsafe.policies.FailsafePoliciesListeners.*;

public class FailsafePolicies {

    public static RetryPolicy<Object> defaultRetryPolicy() {
        return RetryPolicy.builder()
                .handle(AssertionError.class)
                .withMaxRetries(5)
                .withDelay(Duration.ofSeconds(2))
                .onFailedAttempt(onFailedAttemptListener())
                .onRetry(onRetryListener())
                .onFailure(onFailureListener()).build();
    }

    public static RetryPolicy<Object> backoffRetryPolicy() {
        return RetryPolicy.builder()
                .handle(AssertionError.class)
                .withBackoff(Duration.ofSeconds(1), Duration.ofSeconds(10))
                .withMaxRetries(5)
                .onFailedAttempt(onFailedAttemptListener())
                .onRetry(onRetryListener())
                .onFailure(onFailureListener()).build();
    }

    public static RetryPolicy<Object> fastRetryPolicy() {
        return RetryPolicy.builder()
                .handle(AssertionError.class)
                .withMaxRetries(3)
                .withDelay(Duration.ofMillis(500))
                .onFailedAttempt(onFailedAttemptListener())
                .onRetry(onRetryListener())
                .onFailure(onFailureListener()).build();
    }
}