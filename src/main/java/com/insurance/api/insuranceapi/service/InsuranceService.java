package com.insurance.api.insuranceapi.service;

import com.insurance.api.insuranceapi.model.Insurance;
import com.insurance.api.insuranceapi.repository.InsuranceRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository repository;

    // if database is empty it fill these data and show results
    @PostConstruct
    public void initInsuranceData() {
        if (repository.count() == 0) { // Insert only if the table is empty
            //inserting data
            Insurance i1 = new Insurance("Health Insurance", "Youth Health Plan", 4000.0, 18, 30, "Any", 20000.0, 50000.0);
            Insurance i2 = new Insurance("Health Insurance", "Senior Citizen Health Plan", 7000.0, 60, 80, "Any", 0.0, 40000.0);
            Insurance i3 = new Insurance("Car Insurance", "Young Driver Protection", 3500.0, 18, 25, "Any", 10000.0, 50000.0);
            Insurance i4 = new Insurance("Car Insurance", "Premium Car Insurance", 8000.0, 26, 60, "Any", 50000.0, 200000.0);
            Insurance i5 = new Insurance("Home Insurance", "Family Home Secure", 7500.0, 25, 60, "Any", 30000.0, 100000.0);
            Insurance i6 = new Insurance("Life Insurance", "Women’s Special Plan", 6000.0, 25, 55, "Female", 30000.0, 80000.0);
            Insurance i7 = new Insurance("Life Insurance", "Men’s Wealth Plan", 6500.0, 25, 55, "Male", 30000.0, 80000.0);

            repository.saveAll(List.of(i1, i2, i3, i4, i5, i6, i7));
        }
    }

    // it will give list of insurances
     public List<Insurance> getAllInsurances() {
         return repository.findAll();
     }

     //to get the insurances
     public Insurance getInsurancebyId(long id){
         return repository.findById(id).orElseThrow(()->new RuntimeException("Insurance Not found"));
     }

     //purchasing insurance will give msg if successfully purchased this insurance
     public String purchaseInsurance(long id){
         Insurance insurance = getInsurancebyId(id);
         return "Insurance purchased successfully : "+ insurance.getName();
     }

     // for the curated list
    public List<Insurance> getCuratedInsurances(int age, String gender, double income) {
        return repository.findByUserDetails(age, gender, income);
    }

    public List<Insurance> getCuratedInsurances(int age , String gender,Double income){
        return repository.findAll().stream()
                .filter(insurance -> age>=insurance.getMinAge() && age <=insurance.getMaxAge()) // it will filters out age
                .filter((insurance -> insurance.getGender().equalsIgnoreCase("any") || //filters out gender
                        insurance.getGender().equalsIgnoreCase(gender)))
                .filter(insurance -> income>=insurance.getMinIncome() && income<= insurance.getMaxIncome()) //filters out income
                .collect(Collectors.toList());

    }

}
