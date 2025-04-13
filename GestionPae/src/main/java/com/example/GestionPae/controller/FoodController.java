package com.example.GestionPae.controller;

import com.example.GestionPae.model.Food;
import com.example.GestionPae.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    //List Food id
    @GetMapping("/list/{id}")
    public List<Food> listFood(@PathVariable Long id) {
        return foodService.listFood(id);
    }

    //Search Food
    @GetMapping("/search")
    public Optional<Food> listNameFood(String foodName) {
        return foodService.searchFood(foodName);
    }

    //Create Food
    @PostMapping("/create")
    public Food createFood(@RequestBody Food food){
        return foodService.createFood(food);
    }

    //Update Food
    @PutMapping("/update")
    public Food updateFood(@RequestBody Food food){
        return foodService.updateFood(food);
    }

    //Delete Food
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            foodService.deleteFood(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
