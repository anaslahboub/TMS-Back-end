package com.izorai.pfa.module2.exceptions;

public class VoyageNotFoundException extends RuntimeException {
    public VoyageNotFoundException(Long id) {
        super("Voyage with id : " + id + " not found");
    }
}
