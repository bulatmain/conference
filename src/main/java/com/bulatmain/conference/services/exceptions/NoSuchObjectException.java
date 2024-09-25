package com.bulatmain.conference.services.exceptions;

public class NoSuchObjectException extends RuntimeException {
    public NoSuchObjectException() {
        super();
    }

    public NoSuchObjectException(final String message) {
        super(message);
    }
}
