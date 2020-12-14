package com.resendes.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.adapters.repository.entities.NotificationStatusEntity;
import com.resendes.usecases.exceptions.DeleteOperationNotSupportedException;
import com.resendes.usecases.exceptions.SchedulingNotFoundException;
import com.resendes.usecases.port.NotificationPersistence;

@ExtendWith(MockitoExtension.class)
public class DeleteSchedulingNotificatonTest {

    @Mock
    private NotificationPersistence notificationPersistence;

    @InjectMocks
    private DeleteSchedulingNotification deleteSchedulingNotification;

    @Test
    void deleteWhenSchedulingNotExists() {
        Long schedulingNotificationId = 1L;

        when(notificationPersistence.findById(schedulingNotificationId)).thenReturn(Optional.empty());

        SchedulingNotFoundException schedulingNotFoundException = assertThrows(SchedulingNotFoundException.class, () -> {
            deleteSchedulingNotification.delete(schedulingNotificationId);
        });

        assertEquals(String.format("Scheduling with id %d not found.", schedulingNotificationId),
                schedulingNotFoundException.getMessage());
    }

    @Test
    void deleteWhenStatusIsNotWaiting() {
        Long schedulingNotificationId = 1L;

        when(notificationPersistence.findById(schedulingNotificationId))
                .thenReturn(Optional.of(new NotificationSchedulingEntity()
                        .setStatus(NotificationStatusEntity.SENDING)));

        DeleteOperationNotSupportedException deleteOperationNotSupportedException =
                assertThrows(DeleteOperationNotSupportedException.class,
                        () -> deleteSchedulingNotification.delete(schedulingNotificationId));

        assertEquals("Delete operation is only available when status notification is WAITING", deleteOperationNotSupportedException.getMessage());
    }

    @Test
    void delete() {
        Long schedulingNotificationId = 1L;

        when(notificationPersistence.findById(schedulingNotificationId))
                .thenReturn(Optional.of(new NotificationSchedulingEntity()
                        .setStatus(NotificationStatusEntity.WAITING)));
        doNothing().when(notificationPersistence).delete(any());

        deleteSchedulingNotification.delete(schedulingNotificationId);

        verify(notificationPersistence).delete(schedulingNotificationId);
    }
}
