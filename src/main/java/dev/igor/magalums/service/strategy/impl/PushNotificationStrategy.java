package dev.igor.magalums.service.strategy.impl;

import dev.igor.magalums.service.strategy.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushNotificationStrategy implements NotificationStrategy {
    private static final Logger log = LoggerFactory.getLogger(PushNotificationStrategy.class);
    @Override
    public void send(String destination, String message) {
        log.info("Sending push notification to destination {}", destination);
    }
}
