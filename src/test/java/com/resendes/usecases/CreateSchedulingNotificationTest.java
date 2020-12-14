package com.resendes.usecases;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.adapters.repository.entities.NotificationStatusEntity;
import com.resendes.adapters.repository.entities.NotificationTypeEntity;
import com.resendes.usecases.port.NotificationPersistence;

@ExtendWith(MockitoExtension.class)
public class CreateSchedulingNotificationTest {

    @Mock
    private NotificationPersistence notificationPersistence;

    @InjectMocks
    private CreateSchedulingNotification createSchedulingNotification;

    @Test
    void create() {
        NotificationSchedulingEntity notificationSchedulingEntity = new NotificationSchedulingEntity()
                .setSchedulingDate(OffsetDateTime.now())
                .setMessage("Hello")
                .setRecipient("xpto@xpto.com")
                .setType(NotificationTypeEntity.EMAIL)
                .setStatus(NotificationStatusEntity.WAITING);

        when(notificationPersistence.create(notificationSchedulingEntity)).thenReturn(notificationSchedulingEntity);

        createSchedulingNotification.create(notificationSchedulingEntity);

        verify(notificationPersistence).create(notificationSchedulingEntity);
    }

}
