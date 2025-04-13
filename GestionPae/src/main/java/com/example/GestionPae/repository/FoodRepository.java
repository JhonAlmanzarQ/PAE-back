package com.example.GestionPae.repository;

import com.example.GestionPae.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByName(String name);

    List<Food> findByUserIdUser(long id);
}
