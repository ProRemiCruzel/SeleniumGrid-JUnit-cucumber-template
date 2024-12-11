package com.selenium.failsafe.policies;

import dev.failsafe.event.EventListener;
import dev.failsafe.event.ExecutionAttemptedEvent;
import dev.failsafe.event.ExecutionCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FailsafePoliciesListeners {

    private static final Logger logger = LoggerFactory.getLogger(FailsafePoliciesListeners.class);

    public static EventListener<ExecutionAttemptedEvent<Object>> onFailedAttemptListener() {
        return e -> logger.info("Attempt failure : {}", e.getLastException().getMessage());
    }

    public static EventListener<ExecutionAttemptedEvent<Object>> onRetryListener() {
        return e -> logger.info("Attempt n°{}...", e.getAttemptCount() + 1);
    }

    public static EventListener<ExecutionCompletedEvent<Object>> onFailureListener() {
        return e -> logger.info("Final failure after {} attempts : {}", e.getAttemptCount(), e.getException().getMessage());
    }
}
