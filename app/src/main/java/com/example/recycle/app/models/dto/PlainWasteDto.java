package com.example.recycle.app.models.dto;

import lombok.Data;
import com.example.recycle.app.models.Waste;

@Data
public class PlainWasteDto {
    private Long id;
    private Long quantity;
    private Long border;
    private Long quantityUntilBonus;
    private String type;

    public static PlainWasteDto from(Waste waste){
        PlainWasteDto plainWasteDto = new PlainWasteDto();
        plainWasteDto.setId(waste.getId());
        plainWasteDto.setQuantity(waste.getQuantity());
        plainWasteDto.setBorder(waste.getBorder());
        plainWasteDto.setQuantityUntilBonus(waste.getQuantityUntilBonus());
        plainWasteDto.setType(waste.getType());
        return plainWasteDto;
    }
}
