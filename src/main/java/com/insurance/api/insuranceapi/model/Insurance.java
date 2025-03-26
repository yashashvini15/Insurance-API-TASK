package com.insurance.api.insuranceapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int minAge;
    private int maxAge;
    private String gender; // "Male", "Female", "Any"
    private double minIncome;
    private double maxIncome;

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getMinIncome() {
        return minIncome;
    }

    public void setMinIncome(double minIncome) {
        this.minIncome = minIncome;
    }

    public double getMaxIncome() {
        return maxIncome;
    }

    public void setMaxIncome(double maxIncome) {
        this.maxIncome = maxIncome;
    }

    public Insurance(){}

    public Insurance(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Insurance(String name, String description, double price, int minAge, int maxAge, String gender, double minIncome, double maxIncome) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.gender = gender;
        this.minIncome = minIncome;
        this.maxIncome = maxIncome;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
