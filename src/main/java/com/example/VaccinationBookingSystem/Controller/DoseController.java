package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Enum.DoseType;
import com.example.VaccinationBookingSystem.Exceptions.DoseAlreadyTakenException;
import com.example.VaccinationBookingSystem.Exceptions.PersonNotFoundException;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    @PostMapping("/get_Dose1")
    public ResponseEntity getDose1(@RequestParam("Id") int personId, @RequestParam("doseType") DoseType doseType){
        try {
            Dose dose = doseService.getDose1(personId, doseType);
            return new ResponseEntity(dose, HttpStatus.CREATED);
        }
        catch (DoseAlreadyTakenException d){
            return new ResponseEntity(d.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (PersonNotFoundException p) {
            return new ResponseEntity(p.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
