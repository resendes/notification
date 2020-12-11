package com.resendes.usecases.port;

import java.util.Optional;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;

public interface NotificationPersistence {

    NotificationSchedulingEntity create(NotificationSchedulingEntity entity);

    Optional<NotificationSchedulingEntity> findById(Long id);

    void delete(Long id);

}
