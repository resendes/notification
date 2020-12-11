package com.resendes.adapters.repository.entities;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION_SCHEDULING")
public class NotificationSchedulingEntity {

    @Id
    @GeneratedValue
    private Long id;

    private OffsetDateTime schedulingDate;

    private String recipient;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationTypeEntity type;

    @Enumerated(EnumType.STRING)
    private NotificationStatusEntity status;

    public Long getId() {
        return id;
    }

    public NotificationSchedulingEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public OffsetDateTime getSchedulingDate() {
        return schedulingDate;
    }

    public NotificationSchedulingEntity setSchedulingDate(OffsetDateTime schedulingDate) {
        this.schedulingDate = schedulingDate;
        return this;
    }

    public String getRecipient() {
        return recipient;
    }

    public NotificationSchedulingEntity setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public NotificationSchedulingEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationTypeEntity getType() {
        return type;
    }

    public NotificationSchedulingEntity setType(NotificationTypeEntity type) {
        this.type = type;
        return this;
    }

    public NotificationStatusEntity getStatus() {
        return status;
    }

    public NotificationSchedulingEntity setStatus(NotificationStatusEntity status) {
        this.status = status;
        return this;
    }
}
