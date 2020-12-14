package com.resendes.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.usecases.exceptions.SchedulingNotFoundException;
import com.resendes.usecases.port.NotificationPersistence;

@ExtendWith(MockitoExtension.class)
public class FindSchedulingNotificationByIdTest {

    @Mock
    private NotificationPersistence notificationPersistence;

    @InjectMocks
    private FindSchedulingNotificationById findSchedulingNotificationById;

    @Test
    void findById() {
        Long schedulingNotificationId = 1L;

        when(notificationPersistence.findById(schedulingNotificationId))
                .thenReturn(Optional.of(new NotificationSchedulingEntity()
                        .setId(schedulingNotificationId)));

        NotificationSchedulingEntity schedulingNotification = findSchedulingNotificationById
                .findById(schedulingNotificationId);

        assertEquals(schedulingNotificationId, schedulingNotification.getId());
    }

    @Test
    void findByIdWhenSchedulingNotExists() {
        Long schedulingNotificationId = 1L;

        when(notificationPersistence.findById(schedulingNotificationId)).thenReturn(Optional.empty());

        SchedulingNotFoundException schedulingNotFoundException = assertThrows(SchedulingNotFoundException.class, () -> {
            findSchedulingNotificationById.findById(schedulingNotificationId);
        });

        assertEquals(String.format("Scheduling with id %d not found.", schedulingNotificationId),
                schedulingNotFoundException.getMessage());
    }

}
