package com.resendes.usecases;

import javax.inject.Singleton;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.adapters.repository.entities.NotificationStatusEntity;
import com.resendes.usecases.port.NotificationPersistence;

@Singleton
public class DeleteScheduleNotification {

    private NotificationPersistence notificationPersistence;

    public DeleteScheduleNotification(NotificationPersistence notificationPersistence) {
        this.notificationPersistence = notificationPersistence;
    }

    public void delete(Long id) {
        NotificationSchedulingEntity notification = notificationPersistence.findById(id)
                .orElseThrow(RuntimeException::new);

        if (NotificationStatusEntity.WAITING != notification.getStatus()) {
            throw new RuntimeException();
        }

        notificationPersistence.delete(id);
    }

}