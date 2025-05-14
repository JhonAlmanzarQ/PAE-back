package com.example.GestionPae.service;

import com.example.GestionPae.model.*;
import com.example.GestionPae.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentConsumptionService {

    @Autowired
    private StudentConsumptionRepository studentConsumptionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private InventorySchoolRepository inventorySchoolRepository;

    //List
    public List<StudentConsumption> listStudentConsumption(Long id) {
        return studentConsumptionRepository.findBySchoolId(id);
    }

    //Create
    public StudentConsumption createStudentConsumption(StudentConsumption studentConsumption) {
        Student student = studentRepository.findById(studentConsumption.getStudent().getIdStudent()).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Menu menu = menuRepository.findById(studentConsumption.getMenu().getIdMenu()).orElseThrow(() -> new RuntimeException("Menu no encontrado"));

        studentConsumption.setStudent(student);
        studentConsumption.setMenu(menu);
        studentConsumption.setCreationDate(LocalDate.now());

        //Update Inventory School
        User school = student.getUser();

        for (Food food : menu.getAlimentos()) {
            InventorySchool inventory = inventorySchoolRepository.findBySchoolAndFood(school, food)
                    .orElseThrow(() -> new RuntimeException("No hay inventario del colegio para el alimento: " + food.getName()));

            if (inventory.getQuantity() < 1) {
                throw new RuntimeException("Inventario insuficiente para el alimento: " + food.getName());
            }

            inventory.setQuantity(inventory.getQuantity() - 1);
            inventorySchoolRepository.save(inventory);
        }

        return studentConsumptionRepository.save(studentConsumption);
    }

    //Update
    public StudentConsumption updateStudentConsumption(StudentConsumption newStudentConsumption) {
        return studentConsumptionRepository.findById(newStudentConsumption.getIdStudentConsumption()).map(studentConsumption -> {
            studentConsumption.setMenu(newStudentConsumption.getMenu());
            studentConsumption.setCreationDate(newStudentConsumption.getCreationDate());

            return studentConsumptionRepository.save(studentConsumption);
        }).orElseThrow(() -> new RuntimeException("Consumo de estudiante no encontrado"));
    }

    //Delete
    public void deleteStudentConsumption(Long id) {
        if (studentConsumptionRepository.existsById(id)) {
            studentConsumptionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Consumo de estudiante no encontrado");
        }
    }

}
