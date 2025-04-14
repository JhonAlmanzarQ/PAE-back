package com.example.GestionPae.controller;

import com.example.GestionPae.model.OrderDetail;
import com.example.GestionPae.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderdetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    //List by id
    @GetMapping("/list/{id}")
    public List<OrderDetail> listOrderDetails(@PathVariable Long id) {
        return orderDetailService.listOrderDetails(id);
    }

    //Create
    @PostMapping("/create")
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.createOrderDetail(orderDetail);
    }

    //Update
    @PutMapping("/update")
    public OrderDetail updateOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.updateOrderDetail(orderDetail);
    }

    //Delete
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOrderDetail(@RequestBody OrderDetail orderDetail) {
        try {
            orderDetailService.delteOrderDetail(orderDetail);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
