package com.example.GestionPae.service;

import com.example.GestionPae.model.Food;
import com.example.GestionPae.model.OrderDetail;
import com.example.GestionPae.model.OrderFood;
import com.example.GestionPae.repository.FoodRepository;
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

    //List OrderDetail id
    public List<OrderDetail> listOrderDetails(Long id) {
        return orderDetailRepository.findByOrderId(id);
    }

    //Create OrderDetail
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        OrderFood orderFood = orderFoodRepository.findById(orderDetail.getOrder().getIdOrder()).orElseThrow(() -> new RuntimeException("Orden de la comida no encontrada"));
        Food food = foodRepository.findById(orderDetail.getFood().getIdFood()).orElseThrow(() -> new RuntimeException("Comida no encontrada"));

        orderDetail.setOrder(orderFood);
        orderDetail.setFood(food);

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
    public void delteOrderDetail(OrderDetail orderDetail) {
        if (orderDetailRepository.existsById(orderDetail.getIdOrderDetail())) {
            orderDetailRepository.deleteById(orderDetail.getIdOrderDetail());
        } else {
            throw new RuntimeException("Detalle de Orden no encontrada");
        }
    }
}
