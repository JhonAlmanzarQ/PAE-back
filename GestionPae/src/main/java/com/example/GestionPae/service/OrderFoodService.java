package com.example.GestionPae.service;

import com.example.GestionPae.enums.Rol;
import com.example.GestionPae.model.Food;
import com.example.GestionPae.model.InventoryOperator;
import com.example.GestionPae.model.OrderFood;
import com.example.GestionPae.model.User;
import com.example.GestionPae.repository.FoodRepository;
import com.example.GestionPae.repository.InventoryOperatorRepository;
import com.example.GestionPae.repository.OrderFoodRepository;
import com.example.GestionPae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderFoodService {

    @Autowired
    private OrderFoodRepository orderFoodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private InventoryOperatorRepository inventoryOperatorRepository;

    //List Order School
    public List<OrderFood> listOrderSchool(Long id) {
        return orderFoodRepository.findBySchool_IdUser(id);
    }

    //List Order Logistics
    public List<OrderFood> listOrderLogistics(Long id) {
        return orderFoodRepository.findByLogistics_IdUser(id);
    }

    //Create Order Food
    public OrderFood createOrderFood(OrderFood orderFood) {
        User logistics = userRepository.findById(orderFood.getLogistics().getIdUser()).orElseThrow(() -> new RuntimeException("Logistics no encontrado"));
        User school = userRepository.findById(orderFood.getSchool().getIdUser()).orElseThrow(() -> new RuntimeException("School no encontrado"));
        Food food = foodRepository.findById(orderFood.getFood().getIdFood()).orElseThrow(() -> new RuntimeException("Food no encontrado"));

        if (logistics.getRol() != Rol.USER_LOGISTICS) {
            throw new RuntimeException("El usuario asignado como logística no tiene el rol USER_LOGISTICS");
        }

        if (school.getRol() != Rol.USER_SCHOOL) {
            throw new RuntimeException("El usuario asignado como escuela no tiene el rol USER_SCHOOL");
        }

        orderFood.setFood(food);
        orderFood.setLogistics(logistics);
        orderFood.setSchool(school);
        orderFood.setStatus("enviada");
        orderFood.setCreationDate(LocalDate.now());

        //Update Inventory Operator
        InventoryOperator inventoryOperator = inventoryOperatorRepository.findByLogisticsAndFood(logistics,food).orElseThrow(() -> new RuntimeException("No hay inventario para el alimento con este operador logístico."));

        Long requestQuantity = orderFood.getQuantity();

        if (inventoryOperator.getQuantity() < requestQuantity) {
            throw new RuntimeException("Inventario insuficiente");
        }

        return orderFoodRepository.save(orderFood);
    }

    //Update Order Food
    public OrderFood updateOrderFood(OrderFood newOrderFood) {
        return orderFoodRepository.findById(newOrderFood.getIdOrder()).map(orderFood -> {
            orderFood.setStatus(newOrderFood.getStatus());
            orderFood.setComment(newOrderFood.getComment());
            orderFood.setDeliveryDate(newOrderFood.getDeliveryDate());
            orderFood.setFood(newOrderFood.getFood());

            return orderFoodRepository.save(orderFood);
        }).orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    //Delete Order Food
    public void deleteOrderFood(Long id) {
        if(orderFoodRepository.existsById(id)) {
            orderFoodRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontro la Orden");
        }
    }
}



