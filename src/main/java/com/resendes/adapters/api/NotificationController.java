package com.resendes.adapters.api;

import javax.validation.Valid;

import com.resendes.adapters.api.models.NotificationScheduleRequest;
import com.resendes.adapters.api.models.NotificationScheduleResponse;
import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.usecases.DeleteSchedulingNotification;
import com.resendes.usecases.FindSchedulingNotificationById;
import com.resendes.usecases.CreateSchedulingNotification;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

@Validated
@Controller("/notifications")
public class NotificationController {

    private CreateSchedulingNotification createSchedulingNotification;
    private DeleteSchedulingNotification deleteSchedulingNotification;
    private FindSchedulingNotificationById findSchedulingNotificationById;

    public NotificationController(CreateSchedulingNotification createSchedulingNotification,
            DeleteSchedulingNotification deleteSchedulingNotification,
            FindSchedulingNotificationById findSchedulingNotificationById) {
        this.createSchedulingNotification = createSchedulingNotification;
        this.deleteSchedulingNotification = deleteSchedulingNotification;
        this.findSchedulingNotificationById = findSchedulingNotificationById;
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    HttpResponse<NotificationScheduleResponse> create(@Body @Valid NotificationScheduleRequest request) {
        NotificationSchedulingEntity notificationSchedulingEntity = createSchedulingNotification.create(request.toEntity());

        return HttpResponse.status(HttpStatus.CREATED)
                .body(new NotificationScheduleResponse(notificationSchedulingEntity));
    }

    @Get("/{id}")
    HttpResponse<NotificationScheduleResponse> findById(Long id) {
        NotificationSchedulingEntity notificationSchedulingEntity = findSchedulingNotificationById.findById(id);

        return HttpResponse.status(HttpStatus.OK)
                .body(new NotificationScheduleResponse(notificationSchedulingEntity));
    }

    @Delete("/{id}")
    HttpResponse delete(Long id) {
        deleteSchedulingNotification.delete(id);

        return HttpResponse.noContent();
    }
}
