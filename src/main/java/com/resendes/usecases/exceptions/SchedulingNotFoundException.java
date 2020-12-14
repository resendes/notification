package com.resendes.usecases.exceptions;

public class SchedulingNotFoundException extends RuntimeException {

    public SchedulingNotFoundException(Long id) {
        super(String.format("Scheduling with id %d not found.", id));
    }

}
