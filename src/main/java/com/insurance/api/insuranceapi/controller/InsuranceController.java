package com.insurance.api.insuranceapi.controller;

import com.insurance.api.insuranceapi.model.Insurance;
import com.insurance.api.insuranceapi.service.InsuranceService;
import com.insurance.api.insuranceapi.utils.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {
    @Autowired
    private InsuranceService service;

    @GetMapping("/all") // ex = http://localhost:8080/api/insurance/all , it will display the list of all insurances
    public List<Insurance> getAllInsurances(){
        return service.getAllInsurances();
    }

    @PostMapping("/purchase/{id}") // ex = http://localhost:8080/api/insurance/purchase/1 for the purchased insurance
    public String purchaseInsurance(@PathVariable Long id){
        return service.purchaseInsurance(id);
    }

    @GetMapping("/curated") //for the curated list
    public List<Insurance> getCuratedInsurances(@RequestParam int age,
                                                @RequestParam String gender,
                                                @RequestParam double income) {
        return service.getCuratedInsurances(age, gender, income);
    }


    @GetMapping("/download/{id}") // ex = http://localhost:8080/api/insurance/download/1 // it will display the downloaded policy document
    public ResponseEntity<Resource> downloadPolicy(@PathVariable Long id)throws Exception{
        Insurance insurance = service.getInsurancebyId(id);
        PdfGenerator.generatePolicyPdf(insurance.getName());

        Path path = Paths.get("policy.pdf");
        Resource resources = new UrlResource(path.toUri());


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = "+path.getFileName().toString())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resources);
    }
}
