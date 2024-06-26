package com.example.recycle.app.models.exceptions;

import java.text.MessageFormat;

public class WasteWithThisTypeDoesntExistException extends RuntimeException{
    public WasteWithThisTypeDoesntExistException(final String type){
        super(MessageFormat.format("Waste with type: {0} doesn't exists", type));
    }
}
