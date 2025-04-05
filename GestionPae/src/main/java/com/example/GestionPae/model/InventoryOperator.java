package com.example.GestionPae.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "InventoryOperator")
public class InventoryOperator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventoryOperator;

    @ManyToOne
    @JoinColumn(name = "id_logistics", nullable = false)
    private User logistics;

    @ManyToOne
    @JoinColumn(name = "id_food", nullable = false)
    private User food;

    private Long quantity;

}
