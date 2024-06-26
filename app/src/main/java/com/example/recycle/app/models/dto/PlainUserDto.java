package com.example.recycle.app.models.dto;

import lombok.Data;
import com.example.recycle.app.models.User;

@Data
public class PlainUserDto {
    private Long id;
    private String username;
    private String password;

    public static PlainUserDto from(User user){
        PlainUserDto plainUserDto = new PlainUserDto();
        plainUserDto.setId(user.getId());
        plainUserDto.setUsername(user.getUsername());
        plainUserDto.setPassword(user.getPassword());
        return plainUserDto;
    }
}
