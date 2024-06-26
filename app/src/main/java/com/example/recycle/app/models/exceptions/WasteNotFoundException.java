package com.example.recycle.app.models.exceptions;

import java.text.MessageFormat;

public class WasteNotFoundException extends RuntimeException{
    public WasteNotFoundException(final Long id){
        super(MessageFormat.format("Could not find waste with id: {0}", id));
    }
}
