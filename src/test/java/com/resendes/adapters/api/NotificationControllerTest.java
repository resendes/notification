package com.resendes.adapters.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.OffsetDateTime;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import com.resendes.adapters.api.models.NotificationScheduleRequest;
import com.resendes.adapters.api.models.NotificationScheduleResponse;
import com.resendes.adapters.api.models.NotificationType;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest
public class NotificationControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    public void createSchedulingNotification() {
        String message = "Oi";
        String recipient = "xpto@xpto.com";
        OffsetDateTime schedulingDate = OffsetDateTime.now();

        HttpResponse<NotificationScheduleResponse> exchange = createSchedulingNotification(message, recipient, schedulingDate);

        NotificationScheduleResponse createdSchedulingNotification = exchange.getBody().get();

        assertEquals(HttpStatus.CREATED, exchange.getStatus());

        assertNotNull(createdSchedulingNotification.getId());
        assertEquals(createdSchedulingNotification.getMessage(), message);
        assertEquals(createdSchedulingNotification.getRecipient(), recipient);
        assertEquals(createdSchedulingNotification.getStatus(), "WAITING");
        assertEquals(createdSchedulingNotification.getType(), NotificationType.EMAIL);
    }

    @Test
    public void findSchedulingNotificationById() {
        String message = "Oi";
        String recipient = "xpto@xpto.com";
        OffsetDateTime schedulingDate = OffsetDateTime.now();

        HttpResponse<NotificationScheduleResponse> createdSchedulingNotification = createSchedulingNotification(message, recipient, schedulingDate);

        Long schedulingNotificationId = createdSchedulingNotification.getBody().get().getId();

        HttpResponse<NotificationScheduleResponse> request = client.toBlocking()
                .exchange("/notifications/" + schedulingNotificationId,
                        NotificationScheduleResponse.class);
        NotificationScheduleResponse foundSchedulingNotification = request.getBody().get();

        assertEquals(HttpStatus.OK, request.getStatus());

        assertEquals(schedulingNotificationId, foundSchedulingNotification.getId());
        assertEquals(message, foundSchedulingNotification.getMessage());
        assertEquals(recipient, foundSchedulingNotification.getRecipient());
        assertEquals(schedulingDate.toInstant(), foundSchedulingNotification.getSchedulingDate().toInstant());
        assertEquals("WAITING", foundSchedulingNotification.getStatus());
    }

    @Test
    public void deleteSchedulingNotification() {
        String message = "Oi";
        String recipient = "xpto@xpto.com";
        OffsetDateTime schedulingDate = OffsetDateTime.now();

        HttpResponse<NotificationScheduleResponse> createdSchedulingNotification = createSchedulingNotification(message, recipient, schedulingDate);

        Long schedulingNotificationId = createdSchedulingNotification.getBody().get().getId();

        HttpResponse<NotificationScheduleResponse> findRequest = client.toBlocking()
                .exchange("/notifications/" + schedulingNotificationId, NotificationScheduleResponse.class);

        assertEquals(HttpStatus.OK, findRequest.getStatus());

        HttpResponse<NotificationScheduleResponse> deleteRequest = client.toBlocking()
                .exchange(HttpRequest.DELETE("/notifications/" + schedulingNotificationId));

        assertEquals(HttpStatus.NO_CONTENT, deleteRequest.getStatus());
    }

    private HttpResponse<NotificationScheduleResponse> createSchedulingNotification(String message, String recipient, OffsetDateTime schedulingDate) {
        MutableHttpRequest<NotificationScheduleRequest> post = HttpRequest.POST("/notifications", new NotificationScheduleRequest()
                .setMessage(message)
                .setRecipient(recipient)
                .setSchedulingDate(schedulingDate)
                .setType(NotificationType.EMAIL));

        return client.toBlocking().exchange(post, NotificationScheduleResponse.class);
    }

}
