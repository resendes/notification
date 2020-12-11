package com.resendes.adapters.repository;

import java.util.Optional;

import javax.inject.Singleton;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;
import com.resendes.usecases.port.NotificationPersistence;

@Singleton
public class NotificationPersistenceImp implements NotificationPersistence {

    private NotificationSchedulingRepository notificationSchedulingRepository;

    public NotificationPersistenceImp(NotificationSchedulingRepository notificationSchedulingRepository) {
        this.notificationSchedulingRepository = notificationSchedulingRepository;
    }

    @Override
    public NotificationSchedulingEntity create(NotificationSchedulingEntity entity) {
        return notificationSchedulingRepository.save(entity);
    }

    @Override
    public Optional<NotificationSchedulingEntity> findById(Long id) {
        return notificationSchedulingRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        notificationSchedulingRepository.deleteById(id);
    }
}
