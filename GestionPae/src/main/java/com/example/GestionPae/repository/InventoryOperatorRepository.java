package com.example.GestionPae.repository;

import com.example.GestionPae.model.InventoryOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryOperatorRepository extends JpaRepository<InventoryOperator, Long> {


}
