package com.example.VaccinationBookingSystem.Exceptions;

public class CenterNotFoundException extends RuntimeException{
    public CenterNotFoundException(String message){
        super(message);
    }
}
