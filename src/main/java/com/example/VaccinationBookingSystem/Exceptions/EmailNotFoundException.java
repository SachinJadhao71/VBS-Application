package com.example.VaccinationBookingSystem.Exceptions;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String message){
        super(message);
    }
}
