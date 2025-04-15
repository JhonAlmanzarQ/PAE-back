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
@Table(name = "StudentConsumption")
public class StudentConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStudentConsumption;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_food", nullable = false)
    private Food food;

    private Long quantity;

    private LocalDate creationDate;
}
