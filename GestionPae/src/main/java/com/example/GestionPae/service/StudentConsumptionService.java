package com.example.GestionPae.service;

import com.example.GestionPae.model.*;
import com.example.GestionPae.repository.FoodRepository;
import com.example.GestionPae.repository.InventorySchoolRepository;
import com.example.GestionPae.repository.StudentConsumptionRepository;
import com.example.GestionPae.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentConsumptionService {

    @Autowired
    private StudentConsumptionRepository studentConsumptionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private InventorySchoolRepository inventorySchoolRepository;

    //List
    public List<StudentConsumption> listStudentConsumption(Long id) {
        return studentConsumptionRepository.findBySchoolId(id);
    }

    //Create
    public StudentConsumption createStudentConsumption(StudentConsumption studentConsumption) {
        Student student = studentRepository.findById(studentConsumption.getStudent().getIdStudent()).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Food food = foodRepository.findById(studentConsumption.getFood().getIdFood()).orElseThrow(() -> new RuntimeException("Comida no encontrada"));

        studentConsumption.setStudent(student);
        studentConsumption.setFood(food);

        //Update Inventory School
        User school = student.getUser();

        InventorySchool inventorySchool = inventorySchoolRepository.findBySchoolAndFood(school, food).orElseThrow(() -> new RuntimeException("Inventario del colegio no encontrado para ese alimento"));

        Long requestQuantity = studentConsumption.getQuantity();
        if (inventorySchool.getQuantity() < requestQuantity) {
            throw new RuntimeException("Inventario Insuficiente");
        }

        inventorySchool.setQuantity(inventorySchool.getQuantity() - requestQuantity);
        inventorySchoolRepository.save(inventorySchool);

        return studentConsumptionRepository.save(studentConsumption);
    }

    //Update
    public StudentConsumption updateStudentConsumption(StudentConsumption newStudentConsumption) {
        return studentConsumptionRepository.findById(newStudentConsumption.getIdStudentConsumption()).map(studentConsumption -> {
            studentConsumption.setFood(newStudentConsumption.getFood());
            studentConsumption.setQuantity(newStudentConsumption.getQuantity());
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
