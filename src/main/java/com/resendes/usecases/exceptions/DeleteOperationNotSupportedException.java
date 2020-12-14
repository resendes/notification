package com.resendes.usecases.exceptions;

public class DeleteOperationNotSupportedException extends RuntimeException {

    public DeleteOperationNotSupportedException() {
        super("Delete operation is only available when status notification is WAITING");
    }

}
