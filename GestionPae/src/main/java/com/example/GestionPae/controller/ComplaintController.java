package com.example.GestionPae.controller;

import com.example.GestionPae.model.Complaint;
import com.example.GestionPae.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    //List
    @GetMapping("/list/{id}")
    public List<Complaint> listComplaints(@PathVariable Long id) {
        return complaintService.listComplaint(id);
    }

    //Create
    @PostMapping("/create")
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        return complaintService.createComplaint(complaint);
    }

    //Update
    @PostMapping("/update")
    public Complaint updateComplaint(@RequestBody Complaint complaint) {
        return complaintService.updateComplaint(complaint);
    }

    //Delete
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteComplaint(@PathVariable Long id) {
        try {
            complaintService.deleteComplaint(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}



