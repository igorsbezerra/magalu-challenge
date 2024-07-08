package dev.igor.magalums.service;

import dev.igor.magalums.controller.dto.ScheduleNotificationDto;
import dev.igor.magalums.model.Notification;
import dev.igor.magalums.model.Status;
import dev.igor.magalums.repository.NotificationRepository;
import dev.igor.magalums.service.strategy.NotificationStrategy;
import dev.igor.magalums.service.strategy.impl.EmailNotificationStrategy;
import dev.igor.magalums.service.strategy.impl.PushNotificationStrategy;
import dev.igor.magalums.service.strategy.impl.SMSNotificationStrategy;
import dev.igor.magalums.service.strategy.impl.WhatsAppNotificationStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private Map<String, NotificationStrategy> strategies = Map.of(
            "email", new EmailNotificationStrategy(),
            "sms", new SMSNotificationStrategy(),
            "push", new PushNotificationStrategy(),
            "whatsapp", new WhatsAppNotificationStrategy()
    );

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findBydId(Long id) {
        return notificationRepository.findById(id);
    }

    public void cancelNotification(Long id) {
        var notification = findBydId(id);
        if (notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            notificationRepository.save(notification.get());
        }
    }

    public void checkAndSend(LocalDateTime date) {
        List<Notification> notifications = notificationRepository.findByStatusInAndDateBefore(
                List.of(Status.Values.PENDING.toStatus(), Status.Values.SUCCESS.toStatus()),
                date
        );
        notifications.forEach(sendNotification());
    }

    private Consumer<Notification> sendNotification() {
        return n -> {
            strategies.get(n.getChannel().getDescription()).send(n.getDestination(), n.getMessage());
            n.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(n);
        };
    }
}
