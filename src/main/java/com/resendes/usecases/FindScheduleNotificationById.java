package com.resendes.usecases;

import javax.inject.Singleton;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.adapters.repository.entities.NotificationStatusEntity;
import com.resendes.usecases.port.NotificationPersistence;

@Singleton
public class FindScheduleNotificationById {

    private NotificationPersistence notificationPersistence;

    public FindScheduleNotificationById(NotificationPersistence notificationPersistence) {
        this.notificationPersistence = notificationPersistence;
    }

    public NotificationSchedulingEntity findById(Long id) {
        return notificationPersistence.findById(id).orElseThrow(RuntimeException::new);
    }

}