package com.example.recycle.app.services;

import jakarta.transaction.Transactional;
import com.example.recycle.app.models.Waste;
import com.example.recycle.app.models.exceptions.WasteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.recycle.app.repositories.WasteRepository;

import java.util.List;

@Service
public class WasteService {
    private final WasteRepository wasteRepository;

    @Autowired
    public WasteService(WasteRepository wasteRepository) {
        this.wasteRepository = wasteRepository;
    }

    public Waste addWaste(Waste waste){
        wasteRepository.save(waste);
        return waste;
    }

    public Waste getWaste(Long id){
    return wasteRepository.findById(id).orElseThrow(() -> new WasteNotFoundException(id));
    }

    public List<Waste> getAllWaste(){
        return wasteRepository.findAll();
    }

    public Waste deleteWaste(Long id){
        Waste waste = getWaste(id);
        wasteRepository.delete(waste);
        return waste;
    }

    @Transactional
    public Waste editWaste(Long id, Waste waste){
        Waste editWaste = getWaste(id);
        editWaste.setBorder(waste.getBorder());
        editWaste.setType(waste.getType());
        editWaste.setQuantity(waste.getQuantity());
        editWaste.setQuantityUntilBonus(waste.getQuantityUntilBonus());
        wasteRepository.save(editWaste);
        return editWaste;
    }
}
