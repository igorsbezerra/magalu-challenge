package dev.igor.magalums.repository;

import dev.igor.magalums.model.Notification;
import dev.igor.magalums.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusInAndDateBefore(List<Status> status, LocalDateTime date);
}
