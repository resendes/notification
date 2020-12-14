package com.resendes.usecases;

import javax.inject.Singleton;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.usecases.exceptions.SchedulingNotFoundException;
import com.resendes.usecases.port.NotificationPersistence;

@Singleton
public class FindSchedulingNotificationById {

    private NotificationPersistence notificationPersistence;

    public FindSchedulingNotificationById(NotificationPersistence notificationPersistence) {
        this.notificationPersistence = notificationPersistence;
    }

    public NotificationSchedulingEntity findById(Long id) {
        return notificationPersistence.findById(id).orElseThrow(() -> new SchedulingNotFoundException(id));
    }

}