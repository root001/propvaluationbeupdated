package com.mcb.abdulbasit.valuation.exception;

public class NotFoundException extends RuntimeException {
    private final Object[] args;

    private final String message;

    public NotFoundException(String message, Object[] args) {
        super(message);
        this.message = message;
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
