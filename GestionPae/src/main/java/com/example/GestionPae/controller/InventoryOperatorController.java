package com.example.GestionPae.controller;

import com.example.GestionPae.model.InventoryOperator;
import com.example.GestionPae.service.InventoryOpertorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventariooperador")
public class InventoryOperatorController {

    @Autowired
    private InventoryOpertorService inventoryOpertorService;

    //List
    public List<InventoryOperator> listInventoryOperator() {
        return inventoryOpertorService.listInvetoryOperator();
    }

    //Create
    public InventoryOperator createInventoryOperator (@RequestBody InventoryOperator inventoryOperator) {
        return inventoryOpertorService.creteInventoryOperator(inventoryOperator);
    }

    //Update
    public InventoryOperator updateInventoryOperator (@RequestBody InventoryOperator inventoryOperator) {
        return inventoryOpertorService.updateInventoryOperator(inventoryOperator);
    }

    //Delete
    public ResponseEntity<?> deleteInventoryOperator (@PathVariable Long id) {
        try {
            inventoryOpertorService.deleteInventoryOperator(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
