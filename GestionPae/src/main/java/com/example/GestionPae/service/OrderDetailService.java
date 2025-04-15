package com.example.GestionPae.service;

import com.example.GestionPae.model.*;
import com.example.GestionPae.repository.FoodRepository;
import com.example.GestionPae.repository.InventoryOperatorRepository;
import com.example.GestionPae.repository.OrderDetailRepository;
import com.example.GestionPae.repository.OrderFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderFoodRepository orderFoodRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private InventoryOperatorRepository inventoryOperatorRepository;

    //List OrderDetail id
    public List<OrderDetail> listOrderDetails(Long id) {
        return orderDetailRepository.findByOrder_IdOrder(id);
    }

    //Create OrderDetail
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        OrderFood orderFood = orderFoodRepository.findById(orderDetail.getOrder().getIdOrder()).orElseThrow(() -> new RuntimeException("Orden de la comida no encontrada"));
        Food food = foodRepository.findById(orderDetail.getFood().getIdFood()).orElseThrow(() -> new RuntimeException("Comida no encontrada"));

        orderDetail.setOrder(orderFood);
        orderDetail.setFood(food);

        //Update Inventory Operator
        User logistics = orderFood.getLogistics();

        InventoryOperator inventory = inventoryOperatorRepository.findByLogisticsAndFood(logistics, food).orElseThrow(() -> new RuntimeException("No hay inventario para el alimento con este operador logístico."));

        Long requestQuantity = orderDetail.getQuantity();
        if (inventory.getQuantity() < requestQuantity) {
            throw new RuntimeException("Inventario insuficiente para " + food.getName() + ". Disponible: " + inventory.getQuantity());
        }
        inventory.setQuantity(inventory.getQuantity() - requestQuantity);
        inventoryOperatorRepository.save(inventory);

        return orderDetailRepository.save(orderDetail);
    }

    //Update OrderDetail
    public OrderDetail updateOrderDetail(OrderDetail newOrderDetail) {
        return orderDetailRepository.findById(newOrderDetail.getIdOrderDetail()).map(orderDetail -> {
            orderDetail.setFood(newOrderDetail.getFood());
            orderDetail.setFood(newOrderDetail.getFood());

            return orderDetailRepository.save(orderDetail);
        }).orElseThrow(() -> new RuntimeException("Detalle de orden no encontrada"));
    }

    //Delete OrderDetail
    public void delteOrderDetail(Long id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
        } else {
            throw new RuntimeException("Detalle de Orden no encontrada");
        }
    }
}
