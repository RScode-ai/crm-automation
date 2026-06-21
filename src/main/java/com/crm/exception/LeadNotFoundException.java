package com.crm.exception;

public class LeadNotFoundException
        extends RuntimeException {

    public LeadNotFoundException(String message) {
        super(message);
    }
}