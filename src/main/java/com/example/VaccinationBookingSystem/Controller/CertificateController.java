package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Service.CertificateService;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CertificateResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificate")
public class CertificateController {


    @Autowired
    CertificateService certificateService;

    @PostMapping("/generate")
    public ResponseEntity generateCertificate(@RequestParam("personId") int personId){
        try {
            CertificateResponseDto responseDto = certificateService.generateCertificate(personId);
            return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.ACCEPTED);
        }

    }
}
