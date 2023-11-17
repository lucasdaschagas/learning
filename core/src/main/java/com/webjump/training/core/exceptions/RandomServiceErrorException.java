package com.webjump.training.core.exceptions;

import org.apache.sling.api.resource.PersistenceException;
import org.json.JSONException;

public class RandomServiceErrorException extends RuntimeException {
    public RandomServiceErrorException(String msg){
        super(msg);
    }
}
