package com.example.VaccinationBookingSystem.Exceptions;

public class personAlreadyExistsException extends RuntimeException{

    public personAlreadyExistsException(String message){
        super(message);
    }
}
