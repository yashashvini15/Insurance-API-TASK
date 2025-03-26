package com.insurance.api.insuranceapi.repository;

import com.insurance.api.insuranceapi.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> { // for the curated list data
    @Query("SELECT i FROM Insurance i WHERE (:age BETWEEN i.minAge AND i.maxAge) " +
            "AND (:gender = i.gender OR i.gender = 'Any') " +
            "AND (:income BETWEEN i.minIncome AND i.maxIncome)")
    List<Insurance> findByUserDetails(@Param("age") int age,
                                      @Param("gender") String gender,
                                      @Param("income") double income);

}
