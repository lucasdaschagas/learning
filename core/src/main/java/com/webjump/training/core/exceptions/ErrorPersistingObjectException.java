package com.webjump.training.core.exceptions;

import org.apache.sling.api.resource.PersistenceException;

import java.io.IOException;

public class ErrorPersistingObjectException extends PersistenceException {
    public ErrorPersistingObjectException(String ex){
        super(ex);
    }
}
