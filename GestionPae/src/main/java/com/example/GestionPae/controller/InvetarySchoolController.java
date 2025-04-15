package com.example.GestionPae.controller;

import com.example.GestionPae.model.InventorySchool;
import com.example.GestionPae.service.InventorySchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventoryschool")
public class InvetarySchoolController {

    @Autowired
    private InventorySchoolService inventorySchoolService;

    //List
    @RequestMapping("/list/{id}")
    public List<InventorySchool> listInventorySchools(@PathVariable Long id) {
        return inventorySchoolService.listInventorySchool(id);
    }

    //Create
    @PostMapping("/create")
    public InventorySchool createInventorySchool(@RequestBody InventorySchool inventorySchool) {
        return inventorySchoolService.createInventorySchool(inventorySchool);
    }

    //Update
    @PostMapping("/update")
    public InventorySchool updateInventorySchool(@RequestBody InventorySchool inventorySchool) {
        return inventorySchoolService.updateInventorySchool(inventorySchool);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInventarySchool(@PathVariable Long id) {
        try {
            inventorySchoolService.deleteInventorySchool(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
