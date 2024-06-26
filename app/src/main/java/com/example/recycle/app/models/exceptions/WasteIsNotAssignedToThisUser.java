package com.example.recycle.app.models.exceptions;

import java.text.MessageFormat;

public class WasteIsNotAssignedToThisUser extends RuntimeException{
    public WasteIsNotAssignedToThisUser(final Long userId, final Long wasteId){
        super(MessageFormat.format("Waste id: {0} is not assigned to user id: {1}", wasteId, userId));
    }
}
