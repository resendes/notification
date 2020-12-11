package com.resendes.usecases;

import javax.inject.Singleton;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.usecases.port.NotificationPersistence;

@Singleton
public class ScheduleNotification {

    private NotificationPersistence notificationPersistence;

    public ScheduleNotification(NotificationPersistence notificationPersistence) {
        this.notificationPersistence = notificationPersistence;
    }

    public NotificationSchedulingEntity create(NotificationSchedulingEntity notification) {
        return notificationPersistence.create(notification);
    }

}