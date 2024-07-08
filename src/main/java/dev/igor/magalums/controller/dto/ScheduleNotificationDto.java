package dev.igor.magalums.controller.dto;

import dev.igor.magalums.model.Channel;
import dev.igor.magalums.model.Notification;
import dev.igor.magalums.model.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDto(LocalDateTime date,
                                      String destination,
                                      String message,
                                      Channel.Values channel) {
    public Notification toNotification() {
        return new Notification(
                date,
                destination,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()
        );
    }
}
