package com.resendes.adapters.repository;

import com.resendes.adapters.repository.entities.NotificationSchedulingEntity;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
interface NotificationSchedulingRepository extends CrudRepository<NotificationSchedulingEntity, Long> {
}
