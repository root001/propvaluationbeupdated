package com.mcb.abdulbasit.valuation.exception;

public class EntityNotFoundException extends NotFoundException {

    public EntityNotFoundException(String... param) {
        super("not-found", param );
    }
}
