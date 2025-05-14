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
@Table(name = "InventorySchool")
public class InventorySchool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventorySchool;

    @ManyToOne
    @JoinColumn(name = "id_school", nullable = false)
    private User school;

    @ManyToOne
    @JoinColumn(name = "id_food", nullable = false)
    private Food food;

    private LocalDate expirationDate;

    private Long quantity;

}
