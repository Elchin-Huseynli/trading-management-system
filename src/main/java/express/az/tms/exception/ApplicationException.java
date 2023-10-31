package express.az.tms.exception;

import express.az.tms.model.enums.Exceptions;

public class ApplicationException extends RuntimeException{
    private final Exceptions exceptions;

    public ApplicationException(Exceptions exceptions) {
        super(exceptions.toString());
        this.exceptions = exceptions;
    }

    public Exceptions getExceptions() {
        return exceptions;
    }

}
