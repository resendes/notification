package com.resendes.adapters.api.handlers;

import javax.inject.Singleton;

import com.resendes.usecases.exceptions.DeleteOperationNotSupportedException;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

@Produces
@Singleton
@Requires(classes = {DeleteOperationNotSupportedException.class, ExceptionHandler.class})
public class DeleteOperationNotSupportedExceptionHandler implements ExceptionHandler<DeleteOperationNotSupportedException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, DeleteOperationNotSupportedException exception) {
        return HttpResponse.badRequest(exception.getMessage());
    }

}
