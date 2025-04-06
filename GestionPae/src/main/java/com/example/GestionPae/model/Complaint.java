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
@Table(name = "Complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComplaint;

    @ManyToOne
    @JoinColumn(name = "id_school", nullable = false)
    private User school;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private OrderFood order;

    private String comment;

    private String status; // pendiente / resuelta
}
