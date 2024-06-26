package com.example.recycle.app.models.exceptions;

import java.text.MessageFormat;

public class WasteAlreadyHasAnUserException extends RuntimeException{
    public WasteAlreadyHasAnUserException(final Long userId, final Long wasteId)
    {
        super(MessageFormat.format("Waste id: {0} is already assigned to user id: {1}", wasteId, userId));
    }
}
