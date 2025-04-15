package com.example.GestionPae.repository;

import com.example.GestionPae.model.Food;
import com.example.GestionPae.model.InventorySchool;
import com.example.GestionPae.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventorySchoolRepository extends JpaRepository<InventorySchool, Long> {

    List<InventorySchool> findBySchool_IdUser(Long idUser);

    Optional<InventorySchool> findBySchoolAndFood(User school, Food food);
}
