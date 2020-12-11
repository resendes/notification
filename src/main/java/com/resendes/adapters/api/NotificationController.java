package com.resendes.adapters.api;

import javax.validation.Valid;

import com.resendes.adapters.api.models.NotificationScheduleRequest;
import com.resendes.adapters.api.models.NotificationScheduleResponse;
import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.usecases.DeleteScheduleNotification;
import com.resendes.usecases.FindScheduleNotificationById;
import com.resendes.usecases.ScheduleNotification;

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

    private ScheduleNotification scheduleNotification;
    private DeleteScheduleNotification deleteScheduleNotification;
    private FindScheduleNotificationById findScheduleNotificationById;

    public NotificationController(ScheduleNotification scheduleNotification,
            DeleteScheduleNotification deleteScheduleNotification) {
        this.scheduleNotification = scheduleNotification;
        this.deleteScheduleNotification = deleteScheduleNotification;
        this.findScheduleNotificationById = findScheduleNotificationById;
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    HttpResponse<NotificationScheduleResponse> create(@Body @Valid NotificationScheduleRequest request) {
        NotificationSchedulingEntity notificationSchedulingEntity = scheduleNotification.create(request.toEntity());

        return HttpResponse.status(HttpStatus.CREATED)
                .body(new NotificationScheduleResponse(notificationSchedulingEntity));
    }

    @Get("/{id}")
    HttpResponse<NotificationScheduleResponse> findById(Long id) {
        NotificationSchedulingEntity notificationSchedulingEntity = findScheduleNotificationById.findById(id);

        return HttpResponse.status(HttpStatus.OK)
                .body(new NotificationScheduleResponse(notificationSchedulingEntity));
    }

    @Delete("/{id}")
    HttpResponse delete(Long id) {
        deleteScheduleNotification.delete(id);

        return HttpResponse.noContent();
    }
}
