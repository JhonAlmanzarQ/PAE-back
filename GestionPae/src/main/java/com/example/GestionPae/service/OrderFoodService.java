package com.example.GestionPae.service;

import com.example.GestionPae.model.OrderFood;
import com.example.GestionPae.model.User;
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

        orderFood.setLogistics(logistics);
        orderFood.setSchool(school);
        orderFood.setStatus("enviada");
        orderFood.setCreationDate(LocalDate.now());

        return orderFoodRepository.save(orderFood);
    }

    //Update Order Food
    public OrderFood updateOrderFood(OrderFood newOrderFood) {
        return orderFoodRepository.findById(newOrderFood.getIdOrder()).map(orderFood -> {
            orderFood.setStatus(newOrderFood.getStatus());
            orderFood.setComment(newOrderFood.getComment());
            orderFood.setDeliveryDate(newOrderFood.getDeliveryDate());

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



