package com.example.recycle.app.models.dto;

import lombok.Data;
import com.example.recycle.app.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private List<WasteDto> wasteListDto = new ArrayList<>();

    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setWasteListDto(user.getWasteList().stream().map(WasteDto::from).collect(Collectors.toList()));
        return userDto;
    }
}
