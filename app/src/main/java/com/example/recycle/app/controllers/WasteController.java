package com.example.recycle.app.controllers;

import com.example.recycle.app.models.Waste;
import com.example.recycle.app.models.dto.PlainWasteDto;
import com.example.recycle.app.models.dto.WasteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.recycle.app.services.WasteService;

import java.util.List;

@RestController
public class WasteController {

    private final WasteService wasteService;

    @Autowired
    public WasteController(WasteService wasteService) {
        this.wasteService = wasteService;
    }


    @GetMapping("getWaste")
    public ResponseEntity<List<WasteDto>> getAllWaste(){
        List<Waste> wasteList = wasteService.getAllWaste();
        List<WasteDto> wasteDtoList = wasteList.stream().map(WasteDto::from).toList();
        return new ResponseEntity<>(wasteDtoList, HttpStatus.OK);
    }

    @GetMapping("getWasteById/{id}")
    public ResponseEntity<WasteDto> getWaste(@PathVariable Long id){
        Waste waste = wasteService.getWaste(id);
        return new ResponseEntity<>(WasteDto.from(waste), HttpStatus.OK);
    }

    @DeleteMapping("deleteWaste/{id}")
    public ResponseEntity<PlainWasteDto> deleteWaste(@PathVariable Long id){
        Waste waste = wasteService.deleteWaste(id);
        return new ResponseEntity<>(PlainWasteDto.from(waste), HttpStatus.OK);
    }

   /* @PutMapping(value = "{id}")
    public ResponseEntity<WasteDto> editWaste(@PathVariable Long id, @RequestBody PlainWasteDto plainWasteDto){
        Waste waste = wasteService.editWaste(id, Waste.from(plainWasteDto));
        return new ResponseEntity<>(WasteDto.from(waste), HttpStatus.OK);
    }

     @PostMapping
    public ResponseEntity<WasteDto> addWaste(@RequestBody final PlainWasteDto plainWasteDto){
        Waste waste = wasteService.addWaste(Waste.from(plainWasteDto));
        return new ResponseEntity<>(WasteDto.from(waste), HttpStatus.OK);
    }*/
}
