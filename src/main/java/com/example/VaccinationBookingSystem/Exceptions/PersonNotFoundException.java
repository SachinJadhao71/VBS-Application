package com.example.VaccinationBookingSystem.Exceptions;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(String message){
        super(message);
    }
}
