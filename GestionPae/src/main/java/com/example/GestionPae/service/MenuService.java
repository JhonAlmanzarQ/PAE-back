package com.example.GestionPae.service;

import com.example.GestionPae.model.Food;
import com.example.GestionPae.model.Menu;
import com.example.GestionPae.model.User;
import com.example.GestionPae.repository.FoodRepository;
import com.example.GestionPae.repository.MenuRepository;
import com.example.GestionPae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    //List id school
    public List<Menu> listMenu(Long id) {
        return menuRepository.findBySchool_IdUser(id);
    }

    //Create
    public Menu createMenu(Menu menu) {
        User school = userRepository.findById(menu.getSchool().getIdUser())
                .orElseThrow(() -> new RuntimeException("Escuela no encontrada"));

        if (menu.getAlimentos().isEmpty()) {
            throw new RuntimeException("El menú debe tener al menos un alimento");
        }

        // Verificar que todos los alimentos existen
        List<Food> alimentos = new ArrayList<>();
        for (Food food : menu.getAlimentos()) {
            Food foodEntity = foodRepository.findById(food.getIdFood())
                    .orElseThrow(() -> new RuntimeException("Comida no encontrada: " + food.getName()));
            alimentos.add(foodEntity);
        }

        menu.setSchool(school);
        menu.setAlimentos(alimentos);

        return menuRepository.save(menu);
    }

    //Delete
    public void deleteMenu(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
        } else {
            throw new RuntimeException("Menú no encontrado");
        }
    }


}
