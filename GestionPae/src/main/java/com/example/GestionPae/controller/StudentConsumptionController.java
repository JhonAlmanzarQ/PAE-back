package com.example.GestionPae.controller;

import com.example.GestionPae.model.StudentConsumption;
import com.example.GestionPae.service.StudentConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentconsumption")
public class StudentConsumptionController {

    @Autowired
    private StudentConsumptionService studentConsumptionService;

    //List
    @GetMapping("/list/{id}")
    public List<StudentConsumption> listStudentConsumption(@PathVariable Long id) {
        return studentConsumptionService.listStudentConsumption(id);
    }

    //Create
    @PostMapping("/create")
    public StudentConsumption createStudentConsumption(@RequestBody StudentConsumption studentConsumption) {
        return studentConsumptionService.createStudentConsumption(studentConsumption);
    }

    //Update
    @PutMapping("/update")
    public  StudentConsumption updateStudentConsumption(@RequestBody StudentConsumption studentConsumption) {
        return studentConsumptionService.updateStudentConsumption(studentConsumption);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudentConsumption(@PathVariable Long id) {
        try {
            studentConsumptionService.deleteStudentConsumption(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
