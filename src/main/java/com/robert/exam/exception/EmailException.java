package com.robert.exam.exception;

public class EmailException extends Exception {
    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(Throwable e) {
        super(e);
    }


}
