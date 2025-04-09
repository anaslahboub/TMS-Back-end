package com.izorai.pfa.module2.exceptions;

public class ContientNotFoundException extends RuntimeException {
    public ContientNotFoundException(Long id) {
        super("Contient with id " + id + " not found");
    }
}
