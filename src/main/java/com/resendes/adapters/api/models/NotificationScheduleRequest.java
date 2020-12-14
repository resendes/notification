package com.resendes.adapters.api.models;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.adapters.repository.entities.NotificationStatusEntity;
import com.resendes.adapters.repository.entities.NotificationTypeEntity;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class NotificationScheduleRequest {

    @NotNull
    private OffsetDateTime schedulingDate;

    @NotBlank
    private String recipient;

    @NotBlank
    private String message;

    @NotNull
    private NotificationType type;

    public OffsetDateTime getSchedulingDate() {
        return schedulingDate;
    }

    public NotificationScheduleRequest setSchedulingDate(OffsetDateTime schedulingDate) {
        this.schedulingDate = schedulingDate;
        return this;
    }

    public String getRecipient() {
        return recipient;
    }

    public NotificationScheduleRequest setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public NotificationScheduleRequest setMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationType getType() {
        return type;
    }

    public NotificationScheduleRequest setType(NotificationType type) {
        this.type = type;
        return this;
    }

    public NotificationSchedulingEntity toEntity() {
        return new NotificationSchedulingEntity()
                .setMessage(getMessage())
                .setRecipient(getRecipient())
                .setStatus(NotificationStatusEntity.WAITING)
                .setType(NotificationTypeEntity.valueOf(getType().name()))
                .setSchedulingDate(getSchedulingDate());
    }
}
