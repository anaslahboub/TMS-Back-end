package com.izorai.pfa.module2.exceptions;

public class PhysiqueNotFoundException extends RuntimeException {
    public PhysiqueNotFoundException(Long  id)
    {
        super(" physique with "+ id  + " not found");
    }
}
