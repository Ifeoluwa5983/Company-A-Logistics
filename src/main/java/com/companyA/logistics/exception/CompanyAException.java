package com.companyA.logistics.exception;

public class CompanyAException extends Exception{

    public CompanyAException() {
        super();
    }

    public CompanyAException(String message) {
        super(message);
    }

    public CompanyAException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompanyAException(Throwable cause) {
        super(cause);
    }

    protected CompanyAException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
