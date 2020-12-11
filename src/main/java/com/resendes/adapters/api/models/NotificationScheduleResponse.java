package com.resendes.adapters.api.models;

import java.time.OffsetDateTime;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class NotificationScheduleResponse {

    private Long id;
    private OffsetDateTime schedulingDate;
    private String recipient;
    private String message;
    private NotificationType type;
    private String status;

    public NotificationScheduleResponse() {
    }

    public NotificationScheduleResponse(NotificationSchedulingEntity entity) {
        id = entity.getId();
        schedulingDate = entity.getSchedulingDate();
        recipient = entity.getRecipient();
        message = entity.getMessage();
        type = NotificationType.valueOf(entity.getType().name());
        status = entity.getStatus().name();
    }

    public Long getId() {
        return id;
    }

    public NotificationScheduleResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public OffsetDateTime getSchedulingDate() {
        return schedulingDate;
    }

    public NotificationScheduleResponse setSchedulingDate(OffsetDateTime schedulingDate) {
        this.schedulingDate = schedulingDate;
        return this;
    }

    public String getRecipient() {
        return recipient;
    }

    public NotificationScheduleResponse setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public NotificationScheduleResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationType getType() {
        return type;
    }

    public NotificationScheduleResponse setType(NotificationType type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public NotificationScheduleResponse setStatus(String status) {
        this.status = status;
        return this;
    }
}