package dev.igor.magalums.scheduler;

import dev.igor.magalums.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class MagaluTaskScheduler {
    private static final Logger logger = LoggerFactory.getLogger(MagaluTaskScheduler.class);

    private final NotificationService service;

    public MagaluTaskScheduler(NotificationService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void checkTasks() {
        var now = LocalDateTime.now();
        logger.info("Running at {}", now);
        service.checkAndSend(now);
    }
}
