package com.example.GestionPae.repository;

import com.example.GestionPae.model.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {

    List<OrderFood> findBySchool_IdUser(Long idUser);
    List<OrderFood> findByLogistics_IdUser(Long idUser);



}
