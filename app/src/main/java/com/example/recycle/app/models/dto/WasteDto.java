package com.example.recycle.app.models.dto;

import lombok.Data;
import com.example.recycle.app.models.Waste;

import java.util.Objects;

@Data
public class WasteDto {
    private Long id;
    private Long quantity;
    private Long border;
    private Long quantityUntilBonus;
    private String type;
    private PlainUserDto plainUserDto;

    public static WasteDto from(Waste waste){
        WasteDto wasteDto = new WasteDto();
        wasteDto.setId(waste.getId());
        wasteDto.setQuantity(waste.getQuantity());
        wasteDto.setBorder(waste.getBorder());
        wasteDto.setQuantityUntilBonus(waste.getQuantityUntilBonus());
        wasteDto.setType(waste.getType());
        if(Objects.nonNull(waste.getUser()))
            wasteDto.setPlainUserDto(PlainUserDto.from(waste.getUser()));
        return wasteDto;
    }

}
