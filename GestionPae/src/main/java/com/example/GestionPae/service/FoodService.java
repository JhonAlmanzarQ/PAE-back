package com.example.GestionPae.service;

import com.example.GestionPae.model.Food;
import com.example.GestionPae.model.User;
import com.example.GestionPae.repository.FoodRepository;
import com.example.GestionPae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    //List Food
    public List<Food> listFood(Long id) {
        return foodRepository.findByUserIdUser(id);
    }

    //search by name
    public Optional<Food> searchFood(String foodName) {
        return foodRepository.findByName(foodName);
    }

    //Create Food
    public Food createFood(Food food) {
        User user = userRepository.findById(food.getUser().getIdUser()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        food.setUser(user);
        return foodRepository.save(food);
    }

    //Update Food
    public Food updateFood(Food newFood) {
        return foodRepository.findById(newFood.getIdFood()).map(food -> {
            food.setName(newFood.getName());
            food.setDescription(newFood.getDescription());
            food.setType(newFood.getType());
            food.setExpirationDate(newFood.getExpirationDate());

            return foodRepository.save(food);
        }).orElseThrow(() -> new RuntimeException("Comida no encontrada para actualizar"));
    }

    //Delete Food
    public void deleteFood(Long id) {
        if(foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
        } else {
            throw new RuntimeException("Comida no encontrada para eliminar");
        }
    }
}
