package com.example.recycle.app.models;

import com.example.recycle.app.models.dto.PlainUserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user", schema = "wasteApp")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Waste> wasteList = new ArrayList<>();

    public void addWaste(Waste waste){
        wasteList.add(waste);
    }

    public void removeWaste(Waste waste) {
        wasteList.remove(waste);
    }

    public static User from(PlainUserDto plainUserDto)
    {
        User user = new User();
        user.setPassword(plainUserDto.getPassword());
        user.setUsername(plainUserDto.getUsername());
        return user;
    }

    public User() {
        Waste waste1 = new Waste();
        Waste waste2 = new Waste();
        Waste waste3 = new Waste();

        waste1.setQuantity(0L);
        waste2.setQuantity(0L);
        waste3.setQuantity(0L);

        waste1.setBorder(5L);
        waste2.setBorder(20L);
        waste3.setBorder(100L);

        waste1.setQuantityUntilBonus(5L);
        waste2.setQuantityUntilBonus(20L);
        waste3.setQuantityUntilBonus(100L);

        waste1.setType("paper");
        waste2.setType("glass");
        waste3.setType("clothes");

        waste1.setUser(this);
        waste2.setUser(this);
        waste3.setUser(this);

        wasteList.add(waste1);
        wasteList.add(waste2);
        wasteList.add(waste3);

    }


}
