package com.cy.store.service.exception;

public class UsernameIsNull extends ServiceException{
    public UsernameIsNull() {
        super();
    }

    public UsernameIsNull(String message) {
        super(message);
    }

    public UsernameIsNull(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameIsNull(Throwable cause) {
        super(cause);
    }

    protected UsernameIsNull(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
