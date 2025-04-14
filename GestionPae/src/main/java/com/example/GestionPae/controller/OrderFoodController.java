package com.example.GestionPae.controller;

import com.example.GestionPae.model.OrderFood;
import com.example.GestionPae.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderfood")
public class OrderFoodController {

    @Autowired
    public OrderFoodService orderFoodService;

    //List school
    @GetMapping("/shool/{id}")
    public List<OrderFood> ListOrderFoodSchool(@PathVariable Long id) {
        return orderFoodService.listOrderSchool(id);
    }

    //List Logistics
    @GetMapping("/logistics/{id}")
    public List<OrderFood> ListOrderFoodLogistics(@PathVariable Long id) {
        return orderFoodService.listOrderLogistics(id);
    }

    //Create Order Food
    @PostMapping("/create")
    public OrderFood createOrderFood(@RequestBody OrderFood orderFood) {
        return orderFoodService.createOrderFood(orderFood);
    }

    //Update Order Food
    @PutMapping("/update")
    public OrderFood updateOrderFood(@RequestBody OrderFood orderFood) {
        return orderFoodService.updateOrderFood(orderFood);
    }

    //Delete Order Food
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        try {
            orderFoodService.deleteOrderFood(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
