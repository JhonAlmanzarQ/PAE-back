package com.example.GestionPae.service;

import com.example.GestionPae.model.Food;
import com.example.GestionPae.model.InventoryOperator;
import com.example.GestionPae.model.User;
import com.example.GestionPae.repository.FoodRepository;
import com.example.GestionPae.repository.InventoryOperatorRepository;
import com.example.GestionPae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryOpertorService {

    @Autowired
    private InventoryOperatorRepository inventoryOperatorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    //List
    public List<InventoryOperator> listInvetoryOperator() {
        return inventoryOperatorRepository.findAll();
    }

    //Create
    public InventoryOperator creteInventoryOperator(InventoryOperator inventoryOperator) {
        User user = userRepository.findById(inventoryOperator.getLogistics().getIdUser()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Food food = foodRepository.findById(inventoryOperator.getFood().getIdFood()).orElseThrow(() -> new RuntimeException("Comida no encontrada"));

        inventoryOperator.setLogistics(user);
        inventoryOperator.setFood(food);

        return inventoryOperatorRepository.save(inventoryOperator);
    }

    //Update
    public InventoryOperator updateInventoryOperator(InventoryOperator newInventoryOperator) {
        return inventoryOperatorRepository.findById(newInventoryOperator.getIdInventoryOperator()).map(inventoryOperator -> {
            inventoryOperator.setFood(newInventoryOperator.getFood());
            inventoryOperator.setQuantity(newInventoryOperator.getQuantity());

            return inventoryOperatorRepository.save(inventoryOperator);
        }).orElseThrow(() -> new RuntimeException("Inventario de operador no encontrado"));
    }

    //Delete
    public void deleteInventoryOperator(Long id) {
        if(inventoryOperatorRepository.existsById(id)) {
            inventoryOperatorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Inventario de operador no encontrado");
        }
    }

}
