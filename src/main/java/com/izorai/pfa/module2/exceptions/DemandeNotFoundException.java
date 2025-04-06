package com.izorai.pfa.module2.exceptions;

public class DemandeNotFoundException extends RuntimeException {
    public DemandeNotFoundException(Long id) {
        super("Demande de cotation non trouvée avec l'ID: " + id);
    }}

