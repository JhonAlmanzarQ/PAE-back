package com.example.GestionPae.service;

import com.example.GestionPae.model.Complaint;
import com.example.GestionPae.model.OrderFood;
import com.example.GestionPae.model.User;
import com.example.GestionPae.repository.ComplaintRepository;
import com.example.GestionPae.repository.OrderFoodRepository;
import com.example.GestionPae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderFoodRepository orderFoodRepository;

    //List id school
    public List<Complaint> listComplaint(Long id) {
        return complaintRepository.findBySchool_IdUser(id);
    }

    //Create
    public Complaint createComplaint(Complaint complaint) {
        User user = userRepository.findById(complaint.getSchool().getIdUser()).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        OrderFood orderFood = orderFoodRepository.findById(complaint.getOrder().getIdOrder()).orElseThrow(() -> new RuntimeException("orden de comida no encontrada"));

        complaint.setSchool(user);
        complaint.setOrder(orderFood);

        return complaintRepository.save(complaint);
    }

    //Update
    public Complaint updateComplaint(Complaint newComplaint) {
        return complaintRepository.findById(newComplaint.getIdComplaint()).map(complaint -> {
            newComplaint.setComment(newComplaint.getComment());
            newComplaint.setStatus(newComplaint.getStatus());
            newComplaint.setCreationDate(newComplaint.getCreationDate());

            return complaintRepository.save(newComplaint);
        }).orElseThrow(() -> new RuntimeException("Queja no encontrada"));
    }

    //Delete
    public void deleteComplaint(Long id) {
        if (complaintRepository.existsById(id)) {
            complaintRepository.deleteById(id);
        } else {
            throw new RuntimeException("Queja no encontrada");
        }
    }

}
