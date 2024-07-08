package dev.igor.magalums.service.strategy;

public interface NotificationStrategy {
    void send(String destination, String message);
}
