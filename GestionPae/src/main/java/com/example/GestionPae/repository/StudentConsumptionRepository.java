package com.example.GestionPae.repository;

import com.example.GestionPae.model.Food;
import com.example.GestionPae.model.InventorySchool;
import com.example.GestionPae.model.StudentConsumption;
import com.example.GestionPae.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentConsumptionRepository extends JpaRepository<StudentConsumption, Long> {

    @Query("SELECT sc FROM StudentConsumption sc WHERE sc.student.user.idUser = :idSchool")
    List<StudentConsumption> findBySchoolId(@Param("idSchool") Long idSchool);


}
