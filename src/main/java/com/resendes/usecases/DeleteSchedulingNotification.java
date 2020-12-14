package com.resendes.usecases;

import javax.inject.Singleton;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.adapters.repository.entities.NotificationStatusEntity;
import com.resendes.usecases.exceptions.DeleteOperationNotSupportedException;
import com.resendes.usecases.exceptions.SchedulingNotFoundException;
import com.resendes.usecases.port.NotificationPersistence;

@Singleton
public class DeleteSchedulingNotification {

    private NotificationPersistence notificationPersistence;

    public DeleteSchedulingNotification(NotificationPersistence notificationPersistence) {
        this.notificationPersistence = notificationPersistence;
    }

    public void delete(Long id) {
        NotificationSchedulingEntity notification = notificationPersistence.findById(id)
                .orElseThrow(() -> new SchedulingNotFoundException(id));

        if (NotificationStatusEntity.WAITING != notification.getStatus()) {
            throw new DeleteOperationNotSupportedException();
        }

        notificationPersistence.delete(id);
    }

}