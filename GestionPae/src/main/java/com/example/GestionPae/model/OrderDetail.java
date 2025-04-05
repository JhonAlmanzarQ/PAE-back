package com.example.GestionPae.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrderDetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderDetail;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private User order;

    @ManyToOne
    @JoinColumn(name = "id_food", nullable = false)
    private Food food;

    private Long quantity;

}
