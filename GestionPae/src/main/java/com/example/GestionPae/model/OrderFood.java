package com.example.GestionPae.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrderFood")
public class OrderFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @ManyToOne
    @JoinColumn(name = "id_logistics", nullable = false)
    private User logistics;

    @ManyToOne
    @JoinColumn(name = "id_school", nullable = false)
    private User school;

    private String status; // enviada / recibida / cancelada

    private String deliveryDate;

    private String comment;

    private LocalDate creationDate;

}
