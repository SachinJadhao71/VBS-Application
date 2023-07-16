package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Enum.Gender;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Service.PersonService;
import com.example.VaccinationBookingSystem.dto.RequestDto.AddPersonRequestdto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.AddPersonResponsedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    PersonService personService;

    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestdto addPersonRequestdto){

       try{
           AddPersonResponsedto personResponse = personService.addPerson(addPersonRequestdto);
           return new ResponseEntity(personResponse,HttpStatus.CREATED);
       }
       catch (Exception e){
           return new ResponseEntity("Email already Exists",HttpStatus.BAD_REQUEST);
       }

    }

    @PutMapping("/update_mail")
    public ResponseEntity updateEmail(@RequestParam("oldEmail") String oldEmail, @RequestParam("newEmail") String newEmail){
        try {
            String updated = personService.updateEmail(oldEmail, newEmail);
            return new ResponseEntity(updated,HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get all males of age greater than a certain age

    @GetMapping("/get_all")
    public ResponseEntity getAllMalesOfGreaterThanCertainAge(@RequestParam("age") int age){
        List<String> all = personService.getAllMalesOfGreaterThanCertainAge(age);

        return new ResponseEntity(all,HttpStatus.OK);
    }



    // get all females who have taken only dose 1 not dose 2

    @GetMapping("/get_all_female_who_taken_dose1")
    public ResponseEntity getAllFemalesWhoTakenDose1(){
        List<String> all = personService.getAllFemalesWhoTakenDose1();

        return new ResponseEntity(all,HttpStatus.OK);
    }

    // get all the people who are fully vaccinated

    // get all the people who have not taken even a single dose

    // get all females whose age is greater than a particular age and who have taken only dose 1

    // get all males whose age is greater than a particular age and who have taken both
}
