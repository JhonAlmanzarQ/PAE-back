package com.example.GestionPae.controller;

import com.example.GestionPae.model.InventoryOperator;
import com.example.GestionPae.service.InventoryOpertorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventaryoperator")
public class InventoryOperatorController {

    @Autowired
    private InventoryOpertorService inventoryOpertorService;

    //List
    @GetMapping("/list/{id}")
    public List<InventoryOperator> listInventoryOperator(@PathVariable Long id) {
        return inventoryOpertorService.listInvetoryOperator(id);
    }

    //Create
    @PostMapping("/create")
    public InventoryOperator createInventoryOperator (@RequestBody InventoryOperator inventoryOperator) {
        return inventoryOpertorService.creteInventoryOperator(inventoryOperator);
    }

    //Update
    @PutMapping("/update")
    public InventoryOperator updateInventoryOperator (@RequestBody InventoryOperator inventoryOperator) {
        return inventoryOpertorService.updateInventoryOperator(inventoryOperator);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInventoryOperator (@PathVariable Long id) {
        try {
            inventoryOpertorService.deleteInventoryOperator(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
