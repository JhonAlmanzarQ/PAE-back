package com.example.GestionPae.service;

import com.example.GestionPae.model.Food;
import com.example.GestionPae.model.InventorySchool;
import com.example.GestionPae.model.User;
import com.example.GestionPae.repository.FoodRepository;
import com.example.GestionPae.repository.InventorySchoolRepository;
import com.example.GestionPae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventorySchoolService {

    @Autowired
    private InventorySchoolRepository inventorySchoolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    //List
    public List<InventorySchool> listInventorySchool(Long id) {
        return inventorySchoolRepository.findBySchool_IdUser(id);
    }

    //Create
    public InventorySchool createInventorySchool(InventorySchool inventorySchool) {
        User school = userRepository.findById(inventorySchool.getSchool().getIdUser()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Food food = foodRepository.findById(inventorySchool.getFood().getIdFood()).orElseThrow(() -> new RuntimeException("Comida no encontrada"));

        inventorySchool.setSchool(school);
        inventorySchool.setFood(food);

        return inventorySchoolRepository.save(inventorySchool);
    }

    //Update
    public InventorySchool updateInventorySchool(InventorySchool newInventorySchool) {
        return inventorySchoolRepository.findById(newInventorySchool.getIdInventorySchool()).map(inventorySchool -> {
            inventorySchool.setFood(newInventorySchool.getFood());
            inventorySchool.setQuantity(newInventorySchool.getQuantity());

            return inventorySchoolRepository.save(inventorySchool);
        }).orElseThrow(() -> new RuntimeException("Inventario de Colegio no encontrado"));
    }

    //Delete
    public void deleteInventorySchool(Long id) {
        if (inventorySchoolRepository.existsById(id)) {
            inventorySchoolRepository.deleteById(id);
        } else {
            throw new RuntimeException("Inventario de Colegio no encontrado");
        }
    }

}
