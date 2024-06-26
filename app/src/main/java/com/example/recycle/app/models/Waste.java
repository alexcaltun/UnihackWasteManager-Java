package com.example.recycle.app.models;

import com.example.recycle.app.models.dto.PlainWasteDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "waste", schema = "wasteApp")
public class Waste {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantity;
    private Long border;
    private Long quantityUntilBonus;
    private String type;

    @ManyToOne
    private User user;

    public static Waste from(PlainWasteDto plainWasteDto){
        Waste waste = new Waste();
        waste.setQuantity(plainWasteDto.getQuantity());
        waste.setBorder(plainWasteDto.getBorder());
        waste.setQuantityUntilBonus(plainWasteDto.getQuantityUntilBonus());
        waste.setType(plainWasteDto.getType());
        return waste;
    }
}
