package com.resendes.adapters.api.handlers;


import javax.inject.Singleton;

import com.resendes.usecases.exceptions.SchedulingNotFoundException;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

@Produces
@Singleton
@Requires(classes = {SchedulingNotFoundException.class, ExceptionHandler.class})
public class SchedulingNotFoundExceptionHandler implements ExceptionHandler<SchedulingNotFoundException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, SchedulingNotFoundException exception) {
        return HttpResponse.notFound(exception.getMessage());
    }

}
