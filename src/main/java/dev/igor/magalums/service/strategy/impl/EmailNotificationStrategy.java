package dev.igor.magalums.service.strategy.impl;

import dev.igor.magalums.service.strategy.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotificationStrategy implements NotificationStrategy {
    private static final Logger log = LoggerFactory.getLogger(EmailNotificationStrategy.class);
    @Override
    public void send(String destination, String message) {
        log.info("Sending email to {}", destination);
    }
}
