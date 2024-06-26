package com.example.recycle.app.models.exceptions;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(final Long id){
        super(MessageFormat.format("Could not found user with id: {0}", id));
    }
}
