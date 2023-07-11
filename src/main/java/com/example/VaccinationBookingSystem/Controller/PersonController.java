package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    PersonService personService;

    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody Person person){

       try{
           Person personResponse = personService.addPerson(person);
           return new ResponseEntity(personResponse,HttpStatus.CREATED);
       }
       catch (Exception e){
           return new ResponseEntity("Email already Exists",HttpStatus.BAD_REQUEST);
       }

    }
}
